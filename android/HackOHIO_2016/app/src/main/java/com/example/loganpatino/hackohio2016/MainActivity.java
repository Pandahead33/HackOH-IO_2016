package com.example.loganpatino.hackohio2016;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private LoginButton mLoginButton;
    private CallbackManager mCallbackManager;
    private AccessTokenTracker mAccessTokenTracker;
    private String baseUrl = "http://cswebdesign.biz/beacon/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        mCallbackManager = CallbackManager.Factory.create();

        // for testing purposes
        //LoginManager.getInstance().logOut();

        mAccessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                checkUser();
            }
        };

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mLoginButton = (LoginButton) findViewById(R.id.login_button);
        List<String> permissions = Arrays.asList("public_profile", "user_friends");
        mLoginButton.setReadPermissions(permissions);
        // Other app specific specialization

        // Callback registration
        mLoginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("LOGIN TEST", "We're in!");
                AccessToken.setCurrentAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d("LOGIN TEST", "Login cancelled.");
            }

            @Override
            public void onError(FacebookException exception) {
                Log.d("LOGIN TEST", "Error!");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAccessTokenTracker.stopTracking();
    }

    private void checkUser() {
        AccessToken token = AccessToken.getCurrentAccessToken();
        if (token != null) {
            Profile.fetchProfileForCurrentAccessToken();
            Log.d("PROFILE TEST", Profile.getCurrentProfile().getFirstName());

            Profile currentProfile = Profile.getCurrentProfile();
            String id = currentProfile.getId();
            String firstName = currentProfile.getFirstName();
            String lastName = currentProfile.getLastName();
            String photoUrl = String.valueOf(currentProfile.getProfilePictureUri(25, 25));

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            RetrofitUserInterface.UserService service = retrofit.create(RetrofitUserInterface.UserService.class);
            Call<ResponseBody> call = service.setUser(id, firstName, lastName, "testing");

            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    if (response.isSuccessful()) {
                        //Try to get response body
                        BufferedReader reader = null;
                        StringBuilder sb = new StringBuilder();
                        reader = new BufferedReader(new InputStreamReader(response.body().byteStream()));

                        String line;

                        try {
                            while ((line = reader.readLine()) != null) {
                                sb.append(line);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                        String result = sb.toString();
                        Log.d("RESULT", result);
                    }
                    else {
                        //Try to get response body
                        BufferedReader reader = null;
                        StringBuilder sb = new StringBuilder();
                        reader = new BufferedReader(new InputStreamReader(response.errorBody().byteStream()));

                        String line;

                        try {
                            while ((line = reader.readLine()) != null) {
                                sb.append(line);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                        String result = sb.toString();
                        Log.d("RESULT", result);
                    }


                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.e("ERROR", "we failed fam");
                }
            });

            Intent intent = new Intent(getApplicationContext(), MainTabActivity.class);
            startActivity(intent);
            finish();

        }
        else {
            Log.d("PROFILE TEST", "Nobody logged in.");
        }
    }
}
