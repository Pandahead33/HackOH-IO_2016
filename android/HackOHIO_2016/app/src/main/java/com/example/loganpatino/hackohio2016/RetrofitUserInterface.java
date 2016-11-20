package com.example.loganpatino.hackohio2016;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by loganpatino on 11/20/16.
 */

public class RetrofitUserInterface {
    public interface UserService {
        @POST("setuserdata.php")
        Call<ResponseBody> setUser(@Query("userid") String userId,
                                   @Query("firstname") String firstName,
                                   @Query("lastname") String lastName,
                                   @Query("photourl") String photoUrl);

        @GET("getuserdata.php")
        Call<User> getUserData(@Query("userid") String userId);


    }
}
