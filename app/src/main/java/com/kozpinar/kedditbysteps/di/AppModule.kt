package com.kozpinar.kedditbysteps.di

import android.content.Context
import com.kozpinar.kedditbysteps.core.KedditApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by onur on 23.08.2017. KedditBySteps
 */

@Module
class AppModule(val app: KedditApp) {
    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideApplication(): KedditApp = app
}