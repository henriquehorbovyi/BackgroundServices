package com.example

import android.app.Application

/**
 * .:.:.:. Created by @henrywm on 11/03/19 .:.:.:.
 */

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {

    }

    companion object {
        const val CHANNEL_ID = "connectivityServiceChannel"
    }
}