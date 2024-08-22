package com.example.investmentapplication.retrofit

import com.example.investmentapplication.utils.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitModule {

    object RetrofitClient {

        val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

    object ApiClient {
        val apiService: ApiService by lazy {
            RetrofitClient.retrofit.create(ApiService::class.java)
        }
    }
}
