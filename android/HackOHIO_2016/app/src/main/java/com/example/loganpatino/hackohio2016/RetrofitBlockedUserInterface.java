package com.example.loganpatino.hackohio2016;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by loganpatino on 11/20/16.
 */

public class RetrofitBlockedUserInterface {
    public interface BlockUserService {
        @POST("blockuser.php")
        Call<Boolean> blockUser(@Query("userid") String userId, @Query("blockid") String blockId);

        @DELETE("unblockuser.php")
        Call<Boolean> unblockUser(@Query("userid") String userId,
                                  @Query("unblockid") String unblockId);

        @GET("getblockedusers.php")
        Call<List<OtherUser>> getBlockedUsers(@Query("userid") String userId);
    }
}
