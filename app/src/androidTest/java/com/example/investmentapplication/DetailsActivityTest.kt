package com.example.investmentapplication

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.investmentapplication.view.DetailsActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class DetailsActivityTest {

    @Test
    fun checkView() {

        val activityScenario = ActivityScenario.launch(DetailsActivity::class.java)

        Thread.sleep(3000)
        onView(withId(R.id.image)).check(matches(isDisplayed()))
    }
}