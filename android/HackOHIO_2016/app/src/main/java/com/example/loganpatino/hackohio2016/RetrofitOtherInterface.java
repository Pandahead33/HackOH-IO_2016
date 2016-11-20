package com.example.loganpatino.hackohio2016;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by loganpatino on 11/20/16.
 */

public class RetrofitOtherInterface {
    public interface OtherService {
        @GET("checkstatus")
        Call<Integer> checkStatus(@Query("userid") String userId);

        @GET("getrecentmatches")
        Call<List<OtherUser>> getRecentMatches(@Query("userid") String userId);
    }
}
