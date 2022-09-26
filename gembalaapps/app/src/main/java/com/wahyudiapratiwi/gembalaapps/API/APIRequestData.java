package com.wahyudiapratiwi.gembalaapps.API;

import com.wahyudiapratiwi.gembalaapps.Model.Register;
import com.wahyudiapratiwi.gembalaapps.Model.RequestRegister;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIRequestData {
    @Headers({"Accept: application/json"})
    @POST("auth/authentication/register")
    Call<Register> registerresponse(
            @Query("nama-mitra") String email,
            @Query("username") String nama,
            @Query("password") String alamat,
            @Query("email") String no_hp,
            @Query("no_hp") String level,
            @Query("alamat") String password
    );

    @POST("auth/authentication/register")
    Call<ResponseBody> register(
            @Body RequestRegister requestRegister
    );
}
