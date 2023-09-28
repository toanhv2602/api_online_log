package vn.skymapglobal.api_online_log.Api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import vn.skymapglobal.api_online_log.ServiceLogin.LoginResponse;
import vn.skymapglobal.api_online_log.ServiceLogin.LoginRequest;
import vn.skymapglobal.api_online_log.ServiceRegister.RegisterRequest;
import vn.skymapglobal.api_online_log.ServiceRegister.RegisterResponse;
import vn.skymapglobal.api_online_log.User;

public interface ApiSevice {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    ApiSevice apiSevice = new Retrofit.Builder()
            .baseUrl("https://armonitoring.skymapglobal.vn/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiSevice.class);

    @POST("authenticate")
    Call<LoginResponse> Login(@Body LoginRequest loginRequest);
    @POST("register")
    Call<RegisterResponse> Register(@Body RegisterRequest registerRequest);

    @GET("me") // Đường dẫn API để lấy thông tin người dùng
    Call<User> getLoggedInUserInfo(
            @Header("Authorization") String authorizationHeader // Thêm header Authorization với token
    );

    @GET("logout")
    Call<Void> logout();
}
