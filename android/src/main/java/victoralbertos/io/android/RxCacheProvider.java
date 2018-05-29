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

    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    Observable<String>
    getBanner1(Observable<String> info, DynamicKey dynamicKey, EvictProvider evictProvider);

}
