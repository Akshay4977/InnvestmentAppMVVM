package com.example.investmentapplication.viewmodels

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.investmentapplication.models.InvestmentItem
import com.example.investmentapplication.repositories.InvestmentRepo
import com.example.investmentapplication.retrofit.RetrofitModule
import com.example.investmentapplication.utils.Constants
import kotlinx.coroutines.launch

class DashboardViewmodel(application: Application) : AndroidViewModel(application) {

    private val repository: InvestmentRepo

    var _itemList = MutableLiveData<List<InvestmentItem>>()
    var itemList = _itemList

    var _status = MutableLiveData<Int>()
    var status =_status

    init {
        repository =
            InvestmentRepo(
                RetrofitModule.ApiClient.apiService
            )
        getItemList(application.applicationContext)
    }

    fun getItemList(context: Context) {
        viewModelScope.launch {
            val response = repository.getItemList(context)
            when (response.status) {
                Constants.STATUS_SUCCESS -> {
                    _itemList.value = response.investments
                    _status.value = Constants.STATUS_SUCCESS
                }

                Constants.STATUS_ERROR -> {
                    _status.value = Constants.STATUS_ERROR
                }

                Constants.STATUS_MALFORMED -> {
                    _status.value = Constants.STATUS_MALFORMED
                }
            }
        }
    }
}
