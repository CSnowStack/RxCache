package victoralbertos.io.android;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.LifeCache;

/**
 *
 */
public interface RxCacheProvider {

    @LifeCache(duration = 2, timeUnit = TimeUnit.SECONDS)
    Observable<String>
    getBanner1(Observable<String> info, DynamicKey dynamicKey, EvictProvider evictProvider);

    @LifeCache(duration = 2, timeUnit = TimeUnit.SECONDS)
    Observable<String>
    getBanner2(Observable<String> info, DynamicKey dynamicKey, EvictProvider evictProvider);

    @LifeCache(duration = 2, timeUnit = TimeUnit.SECONDS)
    Observable<String>
    getBanner3(Observable<String> info, DynamicKey dynamicKey, EvictProvider evictProvider);

    @LifeCache(duration = 2, timeUnit = TimeUnit.SECONDS)
    Observable<String>
    getBanner4(Observable<String> info, DynamicKey dynamicKey, EvictProvider evictProvider);

    @LifeCache(duration = 2, timeUnit = TimeUnit.SECONDS)
    Observable<String>
    getBanner5(Observable<String> info, DynamicKey dynamicKey, EvictProvider evictProvider);

    @LifeCache(duration = 2, timeUnit = TimeUnit.SECONDS)
    Observable<String>
    getBanner6(Observable<String> info, DynamicKey dynamicKey, EvictProvider evictProvider);

    @LifeCache(duration = 2, timeUnit = TimeUnit.SECONDS)
    Observable<String>
    getBanner7(Observable<String> info, DynamicKey dynamicKey, EvictProvider evictProvider);

    @LifeCache(duration = 2, timeUnit = TimeUnit.SECONDS)
    Observable<String>
    getBanner8(Observable<String> info, DynamicKey dynamicKey, EvictProvider evictProvider);

    @LifeCache(duration = 2, timeUnit = TimeUnit.SECONDS)
    Observable<String>
    getBanner9(Observable<String> info, DynamicKey dynamicKey, EvictProvider evictProvider);

}
