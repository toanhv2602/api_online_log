package vn.skymapglobal.api_online_log.ServiceRegister;

import com.google.gson.annotations.SerializedName;

public class RegisterRequest {
    @SerializedName("name")
    private String name;
    @SerializedName("mobile")
    private String mobile;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("password_confirmation")
    private String passwordConfirmation;

    public RegisterRequest(String name, String mobile, String email, String password, String passwordConfirmation) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
    }
}
