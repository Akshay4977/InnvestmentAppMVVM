package com.example.investmentapplication

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.investmentapplication.view.MainActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @Test
    fun checkView() {

        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        Thread.sleep(1000)
        onView(withId(R.id.text_view_app_name)).check(matches(isDisplayed()))
    }
}