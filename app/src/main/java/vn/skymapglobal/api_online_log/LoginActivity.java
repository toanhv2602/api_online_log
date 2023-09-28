package vn.skymapglobal.api_online_log;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.skymapglobal.api_online_log.Api.ApiSevice;
import vn.skymapglobal.api_online_log.ServiceLogin.LoginResponse;
import vn.skymapglobal.api_online_log.ServiceLogin.LoginRequest;

public class LoginActivity extends AppCompatActivity {
    private EditText UsernameEditText ;
    private EditText PasswordEditText;
    private Button LoginButton ;
    private TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        UsernameEditText=findViewById(R.id.Username_box);
        PasswordEditText=findViewById(R.id.Password_box);
        LoginButton=findViewById(R.id.Login_button);
        register=findViewById(R.id.dangky_btn);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginRequest loginRequest = new LoginRequest(UsernameEditText.getText().toString(),PasswordEditText.getText().toString());
                ApiSevice.apiSevice.Login(loginRequest).enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful()) {
                            LoginResponse loginResponse = response.body();
                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                            intent.putExtra("token",loginResponse.getToken());
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(LoginActivity.this,response.message(),Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(LoginActivity.this,"Lỗi API",Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}