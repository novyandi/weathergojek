package com.android.weathergojek

import io.mockk.MockKAnnotations
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Before
import org.junit.Ignore

/**
 * Created by Novyandi N. on 02/06/2019.
 * novyandinurahmad@hotmail.com
 */
@Ignore("This is base test for setup")
abstract class BaseUnitTest {

    @Before
    open fun setup() {
        MockKAnnotations.init(this)
    }

    @After
    fun finish() {
        unmockkAll()
    }
}
