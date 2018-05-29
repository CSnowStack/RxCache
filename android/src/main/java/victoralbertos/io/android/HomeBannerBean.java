package victoralbertos.io.android;

import com.google.gson.annotations.SerializedName;

/**
 *
 */
public class HomeBannerBean {
    @SerializedName("img")
    public String url;

    public int res;
    @SerializedName("link")
    public String h5;

    public HomeBannerBean(int res, String h5) {
        this.res = res;
        this.h5 = h5;
    }

}
