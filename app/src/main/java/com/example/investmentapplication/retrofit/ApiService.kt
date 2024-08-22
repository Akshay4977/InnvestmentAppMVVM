package com.example.investmentapplication.retrofit

import com.example.investmentapplication.models.InvestmentResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("/getInvestment")
    fun getInvestment(): Call<InvestmentResponse>
}
