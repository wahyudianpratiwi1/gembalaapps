package com.wahyudiapratiwi.gembalaapps.API;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wahyudiapratiwi.gembalaapps.BuildConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "https://be.gembala.sembadafarm.com/";
    public static String TOKEN = "";
    private static Retrofit retrofit;

    public static Retrofit getRetrofit(){
        return getRetrofit(getOkHttpClient(getInterceptor()));
    }

    public static Retrofit getRetrofit(OkHttpClient okHttpClient){
        if (retrofit == null) {
            final Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofit = new Retrofit.Builder()
                    .client(okHttpClient != null ? okHttpClient : new OkHttpClient())
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }

    public static OkHttpClient getOkHttpClient(Interceptor interceptor) {
        final long networkTimeout = 30L;

        final OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(networkTimeout, TimeUnit.SECONDS);
        httpClientBuilder.writeTimeout(networkTimeout, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(networkTimeout, TimeUnit.SECONDS);
        httpClientBuilder.addInterceptor(interceptor);

        if (BuildConfig.DEBUG) {
            final HttpLoggingInterceptor hli = new HttpLoggingInterceptor();
            hli.level(HttpLoggingInterceptor.Level.BODY);
            httpClientBuilder.addInterceptor(hli);
        }

        return httpClientBuilder.build();
    }

    public static Interceptor getInterceptor() {
        return new Interceptor() {
            @NonNull
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                try {
                    final String auth = chain.request().header("Authorization");
                    final Request.Builder req = chain.request().newBuilder();
                    req.addHeader("Accept", "application/json");
                    req.addHeader("Content-Type", "application/json");
                    if (auth == null) {
                        //req.addHeader("Authorization", "Bearer " + TOKEN);
                    }
                    return chain.proceed(req.build());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return chain.proceed(chain.request());
            }
        };
    }
}
