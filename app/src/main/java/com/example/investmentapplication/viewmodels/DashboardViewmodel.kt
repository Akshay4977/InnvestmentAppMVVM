package com.example.investmentapplication.viewmodels

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.investmentapplication.models.InvestmentItem
import com.example.investmentapplication.repositories.InvestmentRepo
import com.example.investmentapplication.retrofit.RetrofitModule
import com.example.investmentapplication.utils.Constants
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DashboardViewmodel(application: Application) : AndroidViewModel(application) {

    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.e("inside"," Exception L ${throwable.localizedMessage}")
    }
    private val repository: InvestmentRepo

    var _itemList = MutableLiveData<List<InvestmentItem>>()
    var itemList = _itemList

    var _status = MutableLiveData<Int>()
    var status = _status

    init {
        repository =
            InvestmentRepo(
                RetrofitModule.ApiClient.apiService
            )
        getItemList(application.applicationContext)
    }

    fun getItemList(context: Context) {

        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = repository.getItemList(context)
            withContext(Dispatchers.Main) {
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

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}
