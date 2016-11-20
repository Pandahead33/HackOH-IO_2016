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

public class RetrofitPreferredUserInterface {
    public interface PreferredUserService {
        @GET("getpreferredfriends.php")
        Call<List<OtherUser>> getPreferredFriends(@Query("userid") String userId);

        @POST("addpreferredfriend.php")
        Call<Boolean> addPreferredFriend(@Query("userid") String userId,
                                         @Query("preferredfriendid") String preferredFriendId);

        @DELETE("removepreferredfriend.php")
        Call<Boolean> removePreferredFriend(@Query("userid") String userId,
                                            @Query("preferredfriendid") String preferredFriendId);
    }
}
