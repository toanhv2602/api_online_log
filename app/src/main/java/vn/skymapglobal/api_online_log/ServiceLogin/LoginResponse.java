package vn.skymapglobal.api_online_log.ServiceLogin;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    public String getToken() {
        return token;
    }

    @SerializedName("token")
    private String token;
}
