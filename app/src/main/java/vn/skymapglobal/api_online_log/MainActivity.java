package vn.skymapglobal.api_online_log;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.skymapglobal.api_online_log.Api.ApiSevice;

public class MainActivity extends AppCompatActivity {
    private TextView id, name, mobile, email, createAt, updateAt;
    private Button logOutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = findViewById(R.id.id);
        name = findViewById(R.id.name);
        mobile= findViewById(R.id.mobile);
        email=findViewById(R.id.email);
        createAt =findViewById(R.id.createAt);
        updateAt=findViewById(R.id.updateAt);
        logOutBtn = findViewById(R.id.logoutbtn);

        Intent intent = getIntent();
        String token = intent.getStringExtra("token");
        ApiSevice.apiSevice.getLoggedInUserInfo("Bearer " + token).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                        id.setText("id: " + Long.toString(user.getId()));
                        name.setText("Username: " +user.getName());
                        mobile.setText("Phone: " + user.getMobile());
                        email.setText("Email: " + user.getEmail());
                        createAt.setText("Create at: " + user.getCreatedAt());
                        updateAt.setText("Update at: " + user.getUpdatedAt());
                } else {
                    // Xử lý lỗi không thành công
                    Toast.makeText(MainActivity.this,"lỗi rồi",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiSevice.apiSevice.logout().enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(MainActivity.this,response.message(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });


    }
}