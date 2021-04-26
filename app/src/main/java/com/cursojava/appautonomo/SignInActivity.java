package com.cursojava.appautonomo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cursojava.appautonomo.backend_request.AuthenticationCall;
import com.cursojava.appautonomo.backend_request.HttpClient;
import com.cursojava.appautonomo.clients_management.ClientsActivity;
import com.cursojava.appautonomo.model.Login;
import com.cursojava.appautonomo.model.UserResponse;
import com.cursojava.appautonomo.utils.Constants;
import com.cursojava.appautonomo.utils.SharedPreferencesUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {

    private Button btnSignIn;
    private TextView btnSignUp;
    private EditText userEmail;
    private EditText userPassword;
    private ProgressDialog progressDialog;



    private SharedPreferences sp = SharedPreferencesUtil.getSharedPreferences();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        userEmail = findViewById(R.id.user_email);
        userPassword = findViewById(R.id.user_password);

        btnSignIn = findViewById(R.id.btn_signin);
        btnSignIn.setOnClickListener(v -> sendCredentials());

        btnSignUp = findViewById(R.id.btn_signup);
        btnSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
            startActivity(intent);
        });

    }

    private void sendCredentials() {
        progressDialog = new ProgressDialog(SignInActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.setCancelable(false);
        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );
        AuthenticationCall request = HttpClient.getInstance();
        Login login = new Login();
        login.setEmail(userEmail.getText().toString());
        login.setPassword(userPassword.getText().toString());
        Call<UserResponse> response = request.signIn(login);

        response.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                if(response.isSuccessful()) {
                    if(response.code() == 200) {

                        UserResponse user = response.body();
                        sp.edit()
                                .putLong(Constants.USER_ID, user.getId())
                                .putString(Constants.USER_NAME, user.getName())
                                .putBoolean(Constants.FIRST_LOGIN, false)
                                .apply();
                        progressDialog.dismiss();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else {
                    switch (response.code()) {
                        case 400:
                        case 401:
                            Toast.makeText(getApplicationContext(),"Email ou senha incorretos", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                            break;
                        default:
                            Toast.makeText( getApplicationContext(),"Erro de servidor",Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });

    }
}