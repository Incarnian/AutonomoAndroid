package com.cursojava.appautonomo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cursojava.appautonomo.backend_request.AuthenticationCall;
import com.cursojava.appautonomo.backend_request.HttpClient;
import com.cursojava.appautonomo.model.UserRequest;
import com.cursojava.appautonomo.model.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    private EditText userName;
    private EditText userEmail;
    private EditText userPassword;

    private Button btnSignUp;

    private TextView btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        userName = findViewById(R.id.user_name);
        userEmail = findViewById(R.id.user_email);
        userPassword = findViewById(R.id.user_password);
        btnSignUp = findViewById(R.id.btn_signup);
        btnSignIn = findViewById(R.id.btn_signin);

        btnSignUp.setOnClickListener(v -> createUser());
        btnSignIn.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
            startActivity(intent);
        });

    }

    private void createUser() {
        AuthenticationCall request = HttpClient.getInstance();

        UserRequest userRequest = new UserRequest.Builder()
                .name(userName.getText().toString())
                .email(userEmail.getText().toString())
                .password(userPassword.getText().toString())
                .build();

        Call<UserResponse> response = request.createUser(userRequest);

        response.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.isSuccessful()) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("user", response.body());
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Erro ao criar conta", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
    }
}