package com.kozpinar.kedditbysteps.core

/**
 * Created by onur on 24.08.2017. KedditBySteps
 */
interface BaseView<T> {
    fun setPresenter(presenter: T)
}