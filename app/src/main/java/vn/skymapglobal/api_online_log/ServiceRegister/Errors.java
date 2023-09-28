package vn.skymapglobal.api_online_log.ServiceRegister;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Errors {
    @SerializedName("name")
    private List<String> nameErrors;

    @SerializedName("mobile")
    private List<String> mobileErrors;

    @SerializedName("email")
    private List<String> emailErrors;

    @SerializedName("password")
    private List<String> passwordErrors;

    public List<String> getNameErrors() {
        return nameErrors;
    }

    public List<String> getMobileErrors() {
        return mobileErrors;
    }

    public List<String> getEmailErrors() {
        return emailErrors;
    }

    public List<String> getPasswordErrors() {
        return passwordErrors;
    }
}
