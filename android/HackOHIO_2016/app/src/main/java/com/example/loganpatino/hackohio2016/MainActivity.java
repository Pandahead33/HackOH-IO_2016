package com.example.loganpatino.hackohio2016;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
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
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LoginButton mLoginButton;
    private CallbackManager mCallbackManager;
    private AccessTokenTracker mAccessTokenTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        mCallbackManager = CallbackManager.Factory.create();

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
        }
        else {
            Log.d("PROFILE TEST", "Nobody logged in.");
        }
    }
}
