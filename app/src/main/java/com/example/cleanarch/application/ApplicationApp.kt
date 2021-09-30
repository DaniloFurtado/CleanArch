package com.example.cleanarch.application

import android.app.Application
import com.example.cleanarch.di.appModule
import com.example.data.di.dataModule
import com.example.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ApplicationApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            // declare modules
            modules(appModule, dataModule, domainModule)
        }
    }
}
