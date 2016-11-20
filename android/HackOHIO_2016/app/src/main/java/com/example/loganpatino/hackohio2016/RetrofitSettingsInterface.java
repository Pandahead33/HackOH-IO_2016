package com.example.loganpatino.hackohio2016;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by loganpatino on 11/20/16.
 */

public class RetrofitSettingsInterface {
    public interface SettingsService {
        @GET("getreceivenotificationsetting.php")
        Call<Boolean> getReceiveNotificationSetting(@Query("userid") String userId);

        @GET("getdistance.php")
        Call<Integer> getDistance(@Query("userid") String userId);

        @POST("setreceivenotificationsetting.php")
        Call<Boolean> setReceiveNotificationSetting(@Query("userid") String userId,
                                                    @Query("setvalue") boolean notifications);

        @POST("setdistance.php")
        Call<Boolean> setDistance(@Query("userid") String userId,
                                  @Query("distance") int distance);
    }
}
