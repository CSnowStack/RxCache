package victoralbertos.io.android;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 *
 */
public interface Api {
    @GET("api/shop/getBanner")
    Observable<String> getBanner1();

    @GET("api/shop/getBanner")
    Observable<String> getBanner2();
    @GET("api/shop/getBanner")
    Observable<String> getBanner3();
    @GET("api/shop/getBanner")
    Observable<String> getBanner4();
    @GET("api/shop/getBanner")
    Observable<String> getBanner5();
    @GET("api/shop/getBanner")
    Observable<String> getBanner6();
    @GET("api/shop/getBanner")
    Observable<String> getBanner7();
    @GET("api/shop/getBanner")
    Observable<String> getBanner8();
    @GET("api/shop/getBanner")
    Observable<String> getBanner9();
}
