package com.example.loganpatino.hackohio2016;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by loganpatino on 11/19/16.
 */

public class OtherUser {
    private String profilePicUrl;
    private String firstName;

    public OtherUser(String firstName, String profilePicUrl) {
        this.firstName = firstName;
        this.profilePicUrl = profilePicUrl;
    }

    public String getFirstName() {
        return this.firstName;
    }
}
