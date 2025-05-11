package com.example.fitform.network;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.fitform.network.TokenManager;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.IOException;

public class RetrofitInstance {
    private static final String BASE_URL = "https://10.0.2.2:7268";
    private static Retrofit retrofit = null;
    private static TokenManager tokenManager;
    public static Retrofit getRetrofitInstance(Context context) {
        if (retrofit == null) {
            tokenManager = new TokenManager(context);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @NonNull
                        @Override
                        public Response intercept(@NonNull Chain chain) throws IOException {
                            String token = tokenManager.getToken();
                            Request.Builder requestBuilder = chain.request().newBuilder();
                            if (token != null) {
                                requestBuilder.addHeader("Authorization", token);
                            }
                            return chain.proceed(requestBuilder.build());
                        }
                    })
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(UnsafeOkHttpClient.getUnsafeOkHttpClient())
                    .build();
        }
        return retrofit;
    }
    public <T> T createService(Class<T> serviceClass, Context context) {
        return getRetrofitInstance(context).create(serviceClass);
    }

}
