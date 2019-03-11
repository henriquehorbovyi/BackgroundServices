package com.example.workmanager.worker

import android.content.Context
import androidx.work.Worker
import android.net.wifi.WifiManager
import androidx.work.Data

/**
 * .:.:.:. Created by @henrywm on 11/03/19 .:.:.:.
 */
class ConnectivityWorker : Worker() {

    override fun doWork(): Result {
        val level = checkWifiConnectionStatus()
        if (level == INVALID_LEVEL) return Result.FAILURE

        outputData = Data.Builder().putString("WIFI_LEVEL", level).build()
        return Result.SUCCESS
    }

    override fun onWorkFinished(result: Result) {
    }


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