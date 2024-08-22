package com.example.investmentapplication.view

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.investmentapplication.R
import com.example.investmentapplication.utils.Constants

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_details)
        val name = intent.getStringExtra(Constants.NAME)
        val ticker = intent.getStringExtra(Constants.TICKER)
        val principal = intent.getStringExtra(Constants.PRINCIPAL)
        val value = intent.getStringExtra(Constants.VALUE)
        val details = intent.getStringExtra(Constants.DETAILS)

        val nameTextView = findViewById<TextView>(R.id.text_view_details_name)
        nameTextView.text = name

        val tickerTextView = findViewById<TextView>(R.id.text_view_details_ticker)
        if (ticker?.isNotEmpty() == true) {
            tickerTextView.visibility = VISIBLE
            tickerTextView.text = ticker
        } else {
            tickerTextView.visibility = GONE
        }

        val linearLayoutDetails = findViewById<LinearLayout>(R.id.linear_layout_details)
        val principalTextView = findViewById<TextView>(R.id.text_view_details_principal)
        principalTextView.text = principal
        val valueTextView = findViewById<TextView>(R.id.text_view_details_value)
        valueTextView.text = value

        val detailsTextView = findViewById<TextView>(R.id.text_view_details_description)
        detailsTextView.text = details
    }
}