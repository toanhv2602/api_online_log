package vn.skymapglobal.api_online_log.ServiceLogin;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("name")
   private String name;
    @SerializedName("password")
   private String password;

    public LoginRequest(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
