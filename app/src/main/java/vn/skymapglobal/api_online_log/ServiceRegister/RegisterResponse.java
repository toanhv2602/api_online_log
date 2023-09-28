package vn.skymapglobal.api_online_log.ServiceRegister;

import com.google.gson.annotations.SerializedName;

public class RegisterResponse {
    @SerializedName("message")
    private String message;
    @SerializedName("errors")
    private Errors errors;
    @SerializedName("status_code")
    private int statusCode;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Errors getErrors() {
        return errors;
    }

    public void setErrors(Errors errors) {
        this.errors = errors;
    }

    public int getStatus_code() {
        return statusCode;
    }

    public void setStatus_code(int status_code) {
        this.statusCode = status_code;
    }
}
