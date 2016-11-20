package com.example.loganpatino.hackohio2016;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by loganpatino on 11/20/16.
 */

public class RetrofitGroupInterface {
    public interface GroupService {
        @GET("checkgroupstatus")
        Call<Boolean> checkGroupStatus(@Query("userid") String userId);

        @GET("getgroupmembers")
        Call<List<OtherUser>> getGroupMembers(@Query("userid") String userId);

        @GET("getgroupdestination")
        Call<String> getGroupDestination(@Query("userid") String userId);

        @GET("getgroupdestinationoptions")
        Call<List<String>> getGroupDestinationOptions(@Query("userid") String userId);
    }
}
