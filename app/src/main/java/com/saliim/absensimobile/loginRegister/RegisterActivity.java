package com.saliim.absensimobile.loginRegister;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.saliim.absensimobile.R;
import com.saliim.absensimobile.api.API;
import com.saliim.absensimobile.model.registerUser.RegisterUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        toolbar = findViewById(R.id.toolbars);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Register User");

        final EditText etRegNama = findViewById(R.id.et_reg_nama);
        final EditText etRegUsername = findViewById(R.id.et_reg_username);
        final EditText etRegPassword = findViewById(R.id.et_reg_password);
        Button regSubmit = findViewById(R.id.btn_reg_submit);

        regSubmit.setOnClickListener(v -> {
            String inNama2 = LoginActivity.name;
            String inNama = etRegNama.getText().toString();
            String inRegUn = etRegUsername.getText().toString();
            String inRegPw = etRegPassword.getText().toString();

            Log.d("bandinkeun", inNama + "==" + inNama2);

            if (inNama == inNama2){
                etRegNama.setError("Nama Sudah Diambil");
            } else {
                final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this);
                progressDialog.setMessage("Membuat User Baru......");
                progressDialog.show();


                API.registerUsers(inNama, inRegUn, inRegPw).enqueue(new Callback<RegisterUser>() {
                    @Override
                    public void onResponse(Call<RegisterUser> call, Response<RegisterUser> response) {
                        if (response.code() == 200){
                            Log.i("registerUser", response.body().toString());
                            Toast.makeText(RegisterActivity.this, "berhasil", Toast.LENGTH_LONG).show();
                            progressDialog.dismiss();
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterUser> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this, "gagal", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                });
            }

        });
    }

}
