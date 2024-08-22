package com.example.investmentapplication.models

import com.google.gson.annotations.SerializedName

data class InvestmentItem(
    @SerializedName("name")
    val name: String,
    @SerializedName("value")
    val value: Double,
    @SerializedName("ticker")
    val ticker: String,
    @SerializedName("principal")
    val principal: Double,
    @SerializedName("details")
    val details: String

)
