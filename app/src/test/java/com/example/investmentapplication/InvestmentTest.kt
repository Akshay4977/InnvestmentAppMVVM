package com.example.investmentapplication

import com.example.investmentapplication.models.InvestmentItem
import com.example.investmentapplication.models.InvestmentResponse
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase
import org.junit.Test

class InvestmentTest {


    @Test
    fun `test InvestmentResponse empty`() {
        val data: InvestmentResponse = mockk {
            every { status } returns 200
            every { message } returns ""
            every { investments } returns emptyList()
        }

        TestCase.assertEquals(createExpectedResponse(), data)
    }

    private fun createExpectedResponse(): InvestmentResponse {
        return InvestmentResponse(
            status = 200,
            message = "",
            investments = emptyList()
        )
    }

    @Test
    fun `test InvestmentResponse with data`() {
        val data: InvestmentResponse = mockk {
            every { status } returns 200
            every { message } returns ""
            every { investments } returns listOf(
                InvestmentItem(
                    name = "Netflx",
                    ticker = "NFLX",
                    value = 8900000000.0,
                    principal = 5000000000.0,
                    details = "A video production company, streaming giant, and maker of movies."
                )
            )
        }

        TestCase.assertEquals(createExpectedResponseForData(), data)
    }

    private fun createExpectedResponseForData(): InvestmentResponse {
        val list = listOf(
            InvestmentItem(
                name = "Netflx",
                ticker = "NFLX",
                value = 8900000000.0,
                principal = 5000000000.0,
                details = "A video production company, streaming giant, and maker of movies."
            )
        )
        return InvestmentResponse(
            status = 200,
            message = "",
            investments = list
        )
    }
}
