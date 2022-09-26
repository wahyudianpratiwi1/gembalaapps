package com.wahyudiapratiwi.gembalaapps;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.wahyudiapratiwi.gembalaapps.API.APIRequestData;
import com.wahyudiapratiwi.gembalaapps.API.ApiClient;
import com.wahyudiapratiwi.gembalaapps.API.RetroServer;
import com.wahyudiapratiwi.gembalaapps.Model.Register;
import com.wahyudiapratiwi.gembalaapps.Model.RequestRegister;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText nama, username, email, nohp, alamat, katasandi;
    APIRequestData apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        changeStatusBarColor();
        nama = (EditText) findViewById(R.id.nama);
        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        nohp = (EditText) findViewById(R.id.nohp);
        alamat = (EditText) findViewById(R.id.alamat);
        katasandi = (EditText) findViewById(R.id.katasandi);

        nama.setText("Dian");
        username.setText("dian");
        email.setText("dian12@gmail.com");
        nohp.setText("088888888");
        alamat.setText("probolinggo");
        katasandi.setText("12345678");


    }

    private void showMe(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.register_bk_color));
        }
    }

    public void onRegisterClick(View view) {
        showMe("Clicked!");
        Log.d("RegisterActivity API", "clicked: ");
        apiInterface = ApiClient.getRetrofit().create(APIRequestData.class);
        Log.d("TAG", "onMasukClick: " + nama.getText().toString());
        Log.d("TAG", "onMasukClick: " + username.getText().toString());
        Log.d("TAG", "onMasukClick: " + email.getText().toString());
        Log.d("TAG", "onMasukClick: " + nohp.getText().toString());
        Log.d("TAG", "onMasukClick: " + alamat.getText().toString());
        Log.d("TAG", "onMasukClick: " + katasandi.getText().toString());

        Call<ResponseBody> registerCall = apiInterface.register(new RequestRegister(
                username.getText().toString(),email.getText().toString(), nama.getText().toString(), alamat.getText().toString(), nohp.getText().toString(), katasandi.getText().toString()
        ).setLevel("Mitra"));
        registerCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                showMe("RegisterActivity API onResponse: " + response.body());
//                    Log.d("TAG", "==========================================="+response.body());
                if (response.isSuccessful()) {
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                    Log.d("onResponse", "onResponse: " + response.message());
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                showMe("RegisterActivity API onFailure: " + t.getMessage());
                if (t instanceof HttpException) {
                    HttpException exception = (HttpException) t;
                    Response response = exception.response();
                    try {
                        JSONObject jsonObject = new JSONObject(response.errorBody().string());
                        Log.d("Error", jsonObject.optString("message"));
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                Toast.makeText(RegisterActivity.this, "Gagal" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}