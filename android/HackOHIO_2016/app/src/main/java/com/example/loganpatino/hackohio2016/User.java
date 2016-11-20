package com.example.loganpatino.hackohio2016;

import com.google.gson.annotations.SerializedName;

/**
 * Created by loganpatino on 11/20/16.
 */

public class User {
    @SerializedName("userid")
    String userId;

    @SerializedName("firstname")
    String firstName;

    @SerializedName("lastname")
    String lastName;

    @SerializedName("photourl")
    String photoUrl;

    public User(String userId, String firstName, String lastName, String photoUrl) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.photoUrl = photoUrl;
    }
}
