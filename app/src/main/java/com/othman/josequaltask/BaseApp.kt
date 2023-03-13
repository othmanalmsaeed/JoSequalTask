package com.othman.josequaltask

import android.app.Application

class BaseApp: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: BaseApp
            private set
    }
}