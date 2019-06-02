package com.android.weathergojek

import android.app.Activity
import android.app.Application
import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.rule.ActivityTestRule
import com.android.weathergojek.di.component.ActivityComponent
import com.android.weathergojek.di.component.DaggerTestAppComponent
import com.android.weathergojek.di.component.TestAppComponent
import com.android.weathergojek.di.module.MiscellaneousModule
import com.android.weathergojek.matcher.MatcherEx
import org.junit.Before
import org.junit.Rule

/**
 * Created by Novyandi N. on 02/06/2019.
 * novyandinurahmad@hotmail.com
 */
abstract class BaseUITest<T : Activity>(clazz: Class<T>) {
    @get:Rule
    val activityRule: ActivityTestRule<T> = IntentsTestRule(clazz, true, false)
    var application: Application? = null
    var context: Context? = null
    val matcher = MatcherEx
    val instrument = InstrumentationRegistry.getInstrumentation()
    val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
    var testComponent: TestAppComponent? = null

    @Before
    open fun setup() {
        application = targetContext.applicationContext as? Application
        context = application?.baseContext
        setupInjector()
    }

    private fun setupInjector() {
        application?.let {
            with(it as App) {
                testComponent = DaggerTestAppComponent.builder()
                    .applicationComponent(it.getApplicationComponent())
                    .miscellaneousModule(MiscellaneousModule())
                    .build()
                this.setActivityComponent(testComponent as ActivityComponent)
            }
        }
    }

    fun getActivity(): T = activityRule.activity
}
