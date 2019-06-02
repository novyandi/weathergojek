package com.android.weathergojek.matcher

import android.support.test.espresso.matcher.ViewMatchers.hasDescendant
import android.view.View
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.AllOf.allOf
import java.util.*

/**
 * Created by Novyandi N. on 02/06/2019.
 * novyandinurahmad@hotmail.com
 */
object MatcherEx {
    /**
     * Returns a matcher that matches [View]s is visible
     */
    val isVisible: Matcher<View>
        get() = object : TypeSafeMatcher<View>() {
            override fun matchesSafely(view: View): Boolean = view.visibility == View.VISIBLE

            override fun describeTo(description: Description) {
                description.appendText("is visible")
            }
        }

    fun recyclerChildMatcher(vararg matchers: Matcher<in View>): Matcher<View> {
        return hasDescendant(allOf(Arrays.asList<Matcher<in View>>(*matchers)))
    }
}
