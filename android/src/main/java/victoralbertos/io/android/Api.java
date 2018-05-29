package victoralbertos.io.android;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 *
 */
public interface Api {
    @GET("api/shop/getBanner")
    Observable<String> getBanner1();

}
