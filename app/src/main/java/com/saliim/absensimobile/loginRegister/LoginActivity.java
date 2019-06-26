package com.saliim.absensimobile.loginRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.saliim.absensimobile.Admin.AdminActivity;
import com.saliim.absensimobile.MainActivity;
import com.saliim.absensimobile.R;
import com.saliim.absensimobile.api.API;
import com.saliim.absensimobile.model.loginUser.LoginUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Toolbar toolbar;
    public static String name;
    public static String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        toolbar = findViewById(R.id.toolbars);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Login User");

        final EditText etLoginUsername = findViewById(R.id.et_login_username);
        final EditText etLoginPassword = findViewById(R.id.et_login_password);
        Button loginSubmit = findViewById(R.id.btn_login_submit);
        TextView pindahReg = findViewById(R.id.pindah_register);

        loginSubmit.setOnClickListener(v -> {
            String inLogUn = etLoginUsername.getText().toString();
            String inLogPw = etLoginPassword.getText().toString();
            API.loginUsers(inLogUn, inLogPw).enqueue(new Callback<LoginUser>() {
                @Override
                public void onResponse(Call<LoginUser> call, Response<LoginUser> response) {
                    Log.d("login", response.body().toString());

                    String status = response.body().getResult();
                    if (status.equals("1")){
                        name = response.body().getUser().getNama();
                        id = response.body().getUser().getId();
                        String level = response.body().getUser().getVslevel();

                        if (level.equals("normal user")){
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));

                            Toast.makeText(LoginActivity.this, "berhasil login" , Toast.LENGTH_SHORT).show();
                            finish();
                        }else if (level.equals("admin")){
                            startActivity(new Intent(LoginActivity.this, AdminActivity.class));

//                                Toast.makeText(LoginActivity.this, "selamat datang admin" , Toast.LENGTH_SHORT).show();
                            finish();
                        }

                    }else{
                        Toast.makeText(LoginActivity.this, "periksa kembali name dan passwordmu" , Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginUser> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "gagal" , Toast.LENGTH_SHORT).show();
                }
            });
        });

        pindahReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

    }
}
