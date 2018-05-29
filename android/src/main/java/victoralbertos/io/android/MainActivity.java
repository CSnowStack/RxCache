package victoralbertos.io.android;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import victoralbertos.io.android.converterfactory.StringConverterFactory;

/**
 *
 */
public class MainActivity extends Activity {


    @SuppressLint("CheckResult") @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Api api = new Retrofit.Builder()
                .client(new OkHttpClient())
//                .baseUrl("http://mobile.odds99.com/")
                .baseUrl("http://mobile.odds99.com/")
                .addConverterFactory(StringConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(Api.class);


        RxCacheProvider rxCacheProvider = new RxCache.Builder()
                .setIsShouldSaveListener(result -> {
                    if (result.contains("\"error\":0,")) {
                        return true;
                    }
                    return false;
                })
                .useExpiredDataIfLoaderNotAvailable(true)
                .persistence(getApplication().getFilesDir(), new GsonSpeaker())
                .using(RxCacheProvider.class);
        Gson gson = new Gson();


        rxCacheProvider.getBanner1(api.getBanner1(), new DynamicKey("21"), new EvictProvider(!isUnable()))
                .subscribeOn(Schedulers.io())
                .map(s -> gson.<BaseResult<List<HomeBannerBean>>>fromJson(s, new TypeToken<BaseResult<List<HomeBannerBean>>>() {
                }.getType()))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    Log.e("data", new Gson().toJson(data));
                }, error -> {
                    Log.e("error  ", error.toString());
                });


    }


    /**
     * 是否不可用
     */
    public boolean isUnable() {
        ConnectivityManager manager = (ConnectivityManager)
                getApplicationContext().getSystemService(
                        Context.CONNECTIVITY_SERVICE);

        if (manager == null) {
            return true;
        }

        NetworkInfo networkinfo = manager.getActiveNetworkInfo();

        if (networkinfo == null || !networkinfo.isAvailable()) {
            return true;
        }

        return false;


    }

}
