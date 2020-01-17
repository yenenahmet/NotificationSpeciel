package com.spexco.notificationspeciel

import android.provider.ContactsContract.CommonDataKinds.StructuredPostal.COUNTRY
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Matchers.*
import org.junit.After
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    val activityRule  : ActivityTestRule<MainActivity>
            = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testUserButtonClick(){
        Thread.sleep(100)
        onView(withId(R.id.edtText)).perform(typeText("1"),closeSoftKeyboard())
        Thread.sleep(500)
        onView(withId(R.id.btnAdd)).perform(click())
        Thread.sleep(500)

        onView(withId(R.id.controlText)).check(matches(withText("1")))
        Thread.sleep(500)
        spinnerTest1()
    }


    fun spinnerTest(){
        onView(withId(R.id.spinner)).perform(click())

        onData(`is`(instanceOf(String::class.java))).atPosition(0).perform(click())

        onView(withId(R.id.selectedSpinnerItem))
            .check(matches(withText("selected" + "India")))
    }

    fun spinnerTest1(){
        onView(withId(R.id.spinner)).perform(click())
        Thread.sleep(500)
        onData(allOf(`is`(instanceOf(String::class.java)),`is`("USA"))).perform(click())
        Thread.sleep(500)
        onView(withId(R.id.selectedSpinnerItem))
            .check(matches(withText("selected " + "USA")))
        Thread.sleep(2000)
    }

}