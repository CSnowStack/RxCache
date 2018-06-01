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

package io.rx_cache2.internal.cache.memory;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import io.rx_cache2.internal.Memory;
import io.rx_cache2.internal.Record;

public final class ReferenceMapMemory implements Memory {
  private final Map<String, WeakReference<Record>> referenceMap;

  public ReferenceMapMemory() {
    referenceMap =new ConcurrentHashMap<>();
  }

  @Override public  io.rx_cache2.internal.Record getIfPresent(String key) {
    if(referenceMap.get(key)!=null){
      referenceMap.get(key).get();
    }

    return null;
  }

  @Override public  void put(String key, io.rx_cache2.internal.Record record) {
    referenceMap.put(key, new WeakReference<>(record));
  }

  @Override public Set<String> keySet() {
    return referenceMap.keySet();
  }

  @Override public void evict(String key) {
    referenceMap.remove(key);
  }

  @Override public void evictAll() {
    Set<String> keys = referenceMap.keySet();

    synchronized (referenceMap) {
      Iterator iterator = keys.iterator();
      while (iterator.hasNext()) {
        iterator.next();
        iterator.remove();
      }
    }
  }
}
