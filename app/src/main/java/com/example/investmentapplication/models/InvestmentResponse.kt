package com.example.investmentapplication.models

import com.google.gson.annotations.SerializedName


data class InvestmentResponse(
    var status: Int,
    var message: String,
    @SerializedName("investments")
    val investments: List<InvestmentItem>
)
