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
import com.cursojava.appautonomo.model.JwtToken;
import com.cursojava.appautonomo.model.Login;
import com.cursojava.appautonomo.model.TokenBody;
import com.cursojava.appautonomo.utils.Constants;
import com.cursojava.appautonomo.utils.JwtTokenUtil;
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
        Call<JwtToken> response = request.signIn(login);
        System.out.println(response);
        response.enqueue(new Callback<JwtToken>() {
            @Override
            public void onResponse(Call<JwtToken> call, Response<JwtToken> response) {
                if(response.isSuccessful()) {
                    if(response.code() == 200) {

                        TokenBody tokenBody = JwtTokenUtil.decode(response.body());

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra(Constants.TOKEN_BODY, tokenBody);
                        sp.edit()
                                .putLong(Constants.USER_ID, Long.parseLong(tokenBody.getSub()))
                                .putString(Constants.FULL_TOKEN, response.body().getValue())
                                .apply();
                        progressDialog.dismiss();

                        startActivity(intent);
                        finish();
                    }
                }
                else {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Usuário ou senha invalidos", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<JwtToken> call, Throwable t) {
                System.out.println("FALHOUUU ESSA MIERDA");
            }
        });

    }
}