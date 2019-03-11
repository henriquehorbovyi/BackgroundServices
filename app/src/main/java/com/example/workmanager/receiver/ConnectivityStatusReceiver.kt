package com.example.workmanager.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.util.Log
import android.widget.Toast

/**
 * .:.:.:. Created by @henrywm on 11/03/19 .:.:.:.
 */

class ConnectivityStatusReceiver : BroadcastReceiver() {

    private var context: Context? = null

    override fun onReceive(context: Context?, intent: Intent?) {
        this.context = context
        Toast.makeText(context, checkWifiConnectionStatus(), Toast.LENGTH_LONG).show()
    }

    private fun checkWifiConnectionStatus(): String? {
        return context?.let {
            val wifiManager = it.getSystemService(Context.WIFI_SERVICE) as WifiManager
            val numberOfLevels = LEVEL_NUMBERS_OF_CONNECTIVITY
            val wifiInfo = wifiManager.connectionInfo
            val level = WifiManager.calculateSignalLevel(wifiInfo.rssi, numberOfLevels)

            when (level) {
                0 -> LEVEL_NONE
                1 -> LEVEL_NONE
                2 -> LEVEL_POOR
                3 -> LEVEL_MODERATE
                4 -> LEVEL_GOOD
                5 -> LEVEL_EXCELLENT
                else -> INVALID_LEVEL
            }
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