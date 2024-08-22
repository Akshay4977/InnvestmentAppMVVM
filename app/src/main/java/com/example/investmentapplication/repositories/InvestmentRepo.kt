package com.example.investmentapplication.repositories

import android.content.Context
import com.example.investmentapplication.models.InvestmentResponse
import com.example.investmentapplication.retrofit.ApiService
import com.example.investmentapplication.retrofit.RetrofitModule
import com.example.investmentapplication.utils.Constants
import com.example.investmentapplication.utils.Constants.INVESTMENT_EMPTY
import com.example.investmentapplication.utils.Constants.INVESTMENT_ERROR
import com.example.investmentapplication.utils.Constants.INVESTMENT_MALFORMED
import com.example.investmentapplication.utils.Constants.INVESTMENT_SUCCESS
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class InvestmentRepo(apiService: ApiService) {
    val apiService = RetrofitModule.ApiClient.apiService

    suspend fun getItemList(context: Context): InvestmentResponse {

        /*return try {
            val response = apiService.getInvestment().awaitResponse()
            if (response.isSuccessful) {
                val list = response.body()?.investments ?: emptyList()
                return list
            } else {
                return emptyList()
            }
        } catch (exception: Exception) {
            return emptyList()
        }*/

        val fileList = arrayOf(
            INVESTMENT_SUCCESS,
            INVESTMENT_EMPTY,
            INVESTMENT_ERROR,
            INVESTMENT_MALFORMED
        )
        val filename = fileList.random()
        val jsonFileString = getJsonDataFromAsset(context, filename)
        val gson = Gson()
        val investmentResponseType = object : TypeToken<InvestmentResponse>() {}.type
        val investmentResponse: InvestmentResponse =
            gson.fromJson(jsonFileString, investmentResponseType)
        when (filename) {
            INVESTMENT_SUCCESS,
            INVESTMENT_EMPTY ->
                investmentResponse.status = Constants.STATUS_SUCCESS

            INVESTMENT_ERROR ->
                investmentResponse.status = Constants.STATUS_ERROR

            INVESTMENT_MALFORMED ->
                investmentResponse.status = Constants.STATUS_MALFORMED
        }
        return investmentResponse
    }


    private fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }
}
