/*
 * Copyright 2015 Victor Albertos
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.rx_cache2.internal;

import java.util.concurrent.Callable;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.EvictDynamicKeyGroup;
import io.rx_cache2.IsShouldSaveListener;
import io.rx_cache2.internal.cache.GetDeepCopy;

public final class ProcessorProvidersBehaviour implements ProcessorProviders {
  private final io.rx_cache2.internal.cache.TwoLayersCache twoLayersCache;
  private final Boolean useExpiredDataIfLoaderNotAvailable;
  private final IsShouldSaveListener isShouldSaveListener;
  private final GetDeepCopy getDeepCopy;
  private final Observable<Integer> oProcesses;
  private volatile Boolean hasProcessesEnded;

  @Inject public ProcessorProvidersBehaviour(
      io.rx_cache2.internal.cache.TwoLayersCache twoLayersCache,
      Boolean useExpiredDataIfLoaderNotAvailable,
      io.rx_cache2.internal.cache.EvictExpiredRecordsPersistence evictExpiredRecordsPersistence,
      GetDeepCopy getDeepCopy, io.rx_cache2.internal.migration.DoMigrations doMigrations,
      IsShouldSaveListener isShouldSaveListener) {
    this.hasProcessesEnded = false;
    this.twoLayersCache = twoLayersCache;
    this.useExpiredDataIfLoaderNotAvailable = useExpiredDataIfLoaderNotAvailable;
    this.getDeepCopy = getDeepCopy;
    this.oProcesses = startProcesses(doMigrations, evictExpiredRecordsPersistence);
    this.isShouldSaveListener = isShouldSaveListener;
  }

  private Observable<Integer> startProcesses(
      io.rx_cache2.internal.migration.DoMigrations doMigrations,
      final io.rx_cache2.internal.cache.EvictExpiredRecordsPersistence evictExpiredRecordsPersistence) {
    Observable<Integer> oProcesses = doMigrations.react().flatMap(new Function<Integer, ObservableSource<Integer>>() {
          @Override public ObservableSource<Integer> apply(Integer ignore) throws Exception {
            return evictExpiredRecordsPersistence.startEvictingExpiredRecords();
          }
        }).subscribeOn((Schedulers.io())).observeOn(Schedulers.io()).share();


    oProcesses.subscribe(new Consumer<Integer>() {
      @Override public void accept(Integer ignore) throws Exception {
        hasProcessesEnded = true;
      }
    });

    return oProcesses;
  }

  @Override
  public  Observable<String> process(final io.rx_cache2.ConfigProvider configProvider) {
    return Observable.defer(new Callable<ObservableSource<String>>() {
      @Override public ObservableSource<String> call() throws Exception {
        if (hasProcessesEnded) {
          return getData(configProvider);
        }

        return oProcesses.flatMap(new Function<Integer, ObservableSource<String>>() {
          @Override public ObservableSource<String> apply(Integer ignore) throws Exception {
            return getData(configProvider);
          }
        });
      }
    });
  }

  //VisibleForTesting
   Observable<String> getData(final io.rx_cache2.ConfigProvider configProvider) {
    Record record = twoLayersCache.retrieve(configProvider.getProviderKey(), configProvider.getDynamicKey(),
        configProvider.getDynamicKeyGroup(), useExpiredDataIfLoaderNotAvailable,
        configProvider.getLifeTimeMillis(), configProvider.isEncrypted());

    Observable<String> replyObservable;
    //记录存在,且不刷新
    if (record != null && !configProvider.evictProvider().evict()) {
      replyObservable = Observable.just(record.getData());
    } else {
      replyObservable = getDataFromLoader(configProvider, record);
    }

    return replyObservable;
  }

  private Observable<String> getDataFromLoader(final io.rx_cache2.ConfigProvider configProvider,
      final Record record) {
    return configProvider.getLoaderObservable().map(new Function<String,String>() {
      @Override public String apply(String data) throws Exception {
        boolean useExpiredData = configProvider.useExpiredDataIfNotLoaderAvailable() != null ?
            configProvider.useExpiredDataIfNotLoaderAvailable()
            : useExpiredDataIfLoaderNotAvailable;

        if (data == null && useExpiredData && record != null) {
          return record.getData();
        }


        if (data == null) {
          throw new io.rx_cache2.RxCacheException(io.rx_cache2.internal.Locale.NOT_DATA_RETURN_WHEN_CALLING_OBSERVABLE_LOADER
              + " "
              + configProvider.getProviderKey());
        }

        if(isShouldSaveListener!=null&&isShouldSaveListener.shouldSave(data)){
          clearKeyIfNeeded(configProvider);

          twoLayersCache.save(configProvider.getProviderKey(), configProvider.getDynamicKey(),
                  configProvider.getDynamicKeyGroup(), data, configProvider.getLifeTimeMillis(),
                  configProvider.isExpirable(), configProvider.isEncrypted());
        }


        return data;
      }
    }).onErrorReturn(new Function<Throwable, String>() {
      @Override public String apply(Throwable o) throws Exception {
        clearKeyIfNeeded(configProvider);

        boolean useExpiredData = configProvider.useExpiredDataIfNotLoaderAvailable() != null ?
            configProvider.useExpiredDataIfNotLoaderAvailable()
            : useExpiredDataIfLoaderNotAvailable;

        if (useExpiredData && record != null) {
          return  record.getData();
        }

        throw new io.rx_cache2.RxCacheException(io.rx_cache2.internal.Locale.NOT_DATA_RETURN_WHEN_CALLING_OBSERVABLE_LOADER
            + " "
            + configProvider.getProviderKey(), (Throwable) o);
      }
    });
  }

  private void clearKeyIfNeeded(io.rx_cache2.ConfigProvider configProvider) {
    if (!configProvider.evictProvider().evict()) return;

    if (configProvider.evictProvider() instanceof EvictDynamicKeyGroup) {
      twoLayersCache.evictDynamicKeyGroup(configProvider.getProviderKey(),
          configProvider.getDynamicKey().toString(),
          configProvider.getDynamicKeyGroup().toString());
    } else if (configProvider.evictProvider() instanceof EvictDynamicKey) {
      twoLayersCache.evictDynamicKey(configProvider.getProviderKey(),
          configProvider.getDynamicKey().toString());
    } else {
      twoLayersCache.evictProviderKey(configProvider.getProviderKey());
    }
  }

  @Override public Observable<Void> evictAll() {
    return Observable.defer(new Callable<ObservableSource<Void>>() {
      @Override public ObservableSource<Void> call() throws Exception {
        ProcessorProvidersBehaviour.this.twoLayersCache.evictAll();
        return Completable.complete().toObservable();
      }
    });
  }
}
