package com.example.loganpatino.hackohio2016;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by loganpatino on 11/20/16.
 */

public class RetrofitBeaconInterface {
    public interface BeaconService {
        @POST("activatebeacon")
        Call<Boolean> activateBeacon(@Query("userid") String userId,
                                     @Query("latitude") String latitude,
                                     @Query("longitude") String longitude,
                                     @Query("choices") String choices);

        @DELETE("deactivatebeacon")
        Call<Boolean> deactivateBeacon(@Query("userid") String userId);

        @GET("checkbeacon")
        Call<Boolean> checkBeacon(@Query("userid") String userId);
    }
}
