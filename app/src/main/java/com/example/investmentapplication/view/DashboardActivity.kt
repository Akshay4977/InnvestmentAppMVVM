package com.example.investmentapplication.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.investmentapplication.R
import com.example.investmentapplication.adapter.ListAdapter
import com.example.investmentapplication.models.InvestmentItem
import com.example.investmentapplication.utils.Constants
import com.example.investmentapplication.viewmodels.DashboardViewmodel

class DashboardActivity : AppCompatActivity() {
    private lateinit var dashboardViewmodel: DashboardViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e("inside","create")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        dashboardViewmodel = ViewModelProvider(this).get(DashboardViewmodel::class.java)

        dashboardViewmodel.status.observe(this, Observer { it ->
            loadView(dashboardViewmodel, status = it, dashboardViewmodel.itemList.value)
        })
    }

    private fun loadView(
        dashboardViewmodel: DashboardViewmodel,
        status: Int,
        list: List<InvestmentItem>?
    ) {
        val recyclerview = findViewById<RecyclerView>(R.id.recycler_view)
        val emptyView = findViewById<RelativeLayout>(R.id.empty_view)
        val buttonRefresh = findViewById<Button>(R.id.button_refresh)
        buttonRefresh.setOnClickListener {
            dashboardViewmodel.getItemList(this)
        }

        when (status) {
            Constants.STATUS_SUCCESS -> {
                list.let {
                    if (it?.isNotEmpty() == true) {
                        val adapter = ListAdapter(
                            dashboardViewmodel.itemList.value!!,
                            onClick = {
                                navigateToDetailScreen(it)
                            })
                        recyclerview.visibility = VISIBLE
                        emptyView.visibility = GONE
                        recyclerview.layoutManager = LinearLayoutManager(this)
                        recyclerview.adapter = adapter
                    } else {
                        recyclerview.visibility = GONE
                        emptyView.visibility = VISIBLE
                    }
                }
            }

            Constants.STATUS_ERROR -> {
                recyclerview.visibility = GONE
                emptyView.visibility = VISIBLE
            }
        }
    }

    private fun navigateToDetailScreen(item: InvestmentItem) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(Constants.NAME, item.name)
        intent.putExtra(Constants.VALUE, Constants.DOLLAR + item.value.toString())
        intent.putExtra(Constants.PRINCIPAL, Constants.DOLLAR + item.principal.toString())
        intent.putExtra(Constants.TICKER, item.ticker)
        intent.putExtra(Constants.DETAILS, item.details)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        Log.e("inside","start")
    }


    override fun onPause() {

        super.onPause()
        Log.e("inside","pause")
    }

    override fun onResume() {
        super.onResume()
        Log.e("inside","resume")
    }

    override fun onStop() {
        super.onStop()
        Log.e("inside","stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("inside","destroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e("inside","restart")
    }
}