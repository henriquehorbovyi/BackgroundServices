package com.example.workmanager.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.os.IBinder
import android.util.Log
/**
 * .:.:.:. Created by @henrywm on 11/03/19 .:.:.:.
 */
class ConnectivityService : Service() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val level = checkWifiConnectionStatus()
        Log.i("WIFI_CONNECTIVITY", level)
        this.stopSelf()
        return Service.START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun checkWifiConnectionStatus(): String {
        val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val numberOfLevels = LEVEL_NUMBERS_OF_CONNECTIVITY
        val wifiInfo = wifiManager.connectionInfo
        val level = WifiManager.calculateSignalLevel(wifiInfo.rssi, numberOfLevels)

        return when (level) {
            0 -> LEVEL_NONE
            1 -> LEVEL_NONE
            2 -> LEVEL_POOR
            3 -> LEVEL_MODERATE
            4 -> LEVEL_GOOD
            5 -> LEVEL_EXCELLENT
            else -> INVALID_LEVEL
        }
    }


    companion object {
        const val LEVEL_NUMBERS_OF_CONNECTIVITY = 5
        const val LEVEL_NONE = "None"
        const val LEVEL_POOR = "Poor"
        const val LEVEL_MODERATE = "Moderate"
        const val LEVEL_GOOD = "Good"
        const val LEVEL_EXCELLENT = "Excellent"

        const val INVALID_LEVEL = "invalid_level"
    }

}