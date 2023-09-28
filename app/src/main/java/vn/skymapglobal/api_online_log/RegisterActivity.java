package vn.skymapglobal.api_online_log;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.skymapglobal.api_online_log.Api.ApiSevice;
import vn.skymapglobal.api_online_log.ServiceRegister.Errors;
import vn.skymapglobal.api_online_log.ServiceRegister.RegisterRequest;
import vn.skymapglobal.api_online_log.ServiceRegister.RegisterResponse;

public class RegisterActivity extends AppCompatActivity {
    private EditText UsernameEditText, MobileEditText, EmailEditText, PasswordEditText, PasswordConfirmEditText;
    private TextView nameMessage, mobileMessage, emailMessage, passwordMessage, passwordConfirmMessage;
    private Button registerButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        UsernameEditText = findViewById(R.id.Username_box_register);
        MobileEditText = findViewById(R.id.mobile_box_resgister);
        EmailEditText = findViewById(R.id.email_box_resgister);
        PasswordEditText = findViewById(R.id.Password_box_resgister);
        PasswordConfirmEditText = findViewById(R.id.Password_box_resgister_confirm);
        nameMessage = findViewById(R.id.nameMessage);
        mobileMessage = findViewById(R.id.mobileMessage);
        emailMessage = findViewById(R.id.emailMessage);
        passwordConfirmMessage = findViewById(R.id.passwordConfirmMessage);
        passwordMessage = findViewById(R.id.passwordMessage);
        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterRequest registerRequest = new RegisterRequest(UsernameEditText.getText().toString()
                                                                    ,MobileEditText.getText().toString()
                                                                    ,EmailEditText.getText().toString()
                                                                    ,PasswordEditText.getText().toString()
                                                                    ,PasswordConfirmEditText.getText().toString());
                ApiSevice.apiSevice.Register(registerRequest).enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                        if (response.isSuccessful()) {
                            // Xử lý phản hồi thành công
                            RegisterResponse registerResponse = response.body();
                            String message = registerResponse.getMessage();
                        } else if (response.code() == 422) {
                            try {
                                Gson gson = new Gson();
                                RegisterResponse registerResponse = gson.fromJson(response.errorBody().string(), RegisterResponse.class);

                                // Xử lý lỗi cụ thể
                                if (registerResponse != null && registerResponse.getErrors() != null) {
                                    Errors errors = registerResponse.getErrors();
                                    List<String> nameErrors = errors.getNameErrors();
                                    List<String> mobileErrors = errors.getMobileErrors();
                                    List<String> emailErrors = errors.getEmailErrors();
                                    List<String> passwordErrors = errors.getPasswordErrors();
                                    if (nameErrors != null) {
                                        nameMessage.setText(nameErrors.toString());
                                    }else {nameMessage.setText("");}

                                    if (mobileErrors != null) {
                                        mobileMessage.setText(mobileErrors.toString());
                                    }else {mobileMessage.setText("");}

                                    if (emailErrors != null) {
                                        emailMessage.setText(emailErrors.toString());
                                    }else {emailMessage.setText("");}

                                    if (passwordErrors != null) {
                                        if (PasswordEditText.length() < 6){
                                            passwordMessage.setText(passwordErrors.toString());
                                        }else {
                                            passwordMessage.setText("");
                                            passwordConfirmMessage.setText(passwordErrors.toString());
                                        }
                                        nameMessage.setText(nameErrors.toString());
                                    }else {passwordMessage.setText("");passwordConfirmMessage.setText("");}

                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                        }

                    }

                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {

                    }
                });
            }
        });
    }
}