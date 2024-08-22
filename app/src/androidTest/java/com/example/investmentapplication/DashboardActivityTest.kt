package com.example.investmentapplication

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.investmentapplication.view.DashboardActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class DashboardActivityTest {

    @Test
    fun checkView() {

        val activityScenario = ActivityScenario.launch(DashboardActivity::class.java)

        Thread.sleep(3000)
        onView(withId(R.id.empty_view)).check(matches(isDisplayed()))
        onView(withId(R.id.button_refresh)).perform(ViewActions.click())
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
    }
}