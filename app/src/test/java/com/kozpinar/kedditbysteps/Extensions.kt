package com.kozpinar.kedditbysteps

import org.mockito.Mockito

/**
 * Created by onur on 23.08.2017. KedditBySteps
 */

inline fun <reified T: Any> mock(): T = Mockito.mock(T::class.java)
