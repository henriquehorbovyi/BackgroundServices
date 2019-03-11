package com.example.workmanager.ui

import android.content.IntentFilter
import android.net.wifi.WifiManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.workmanager.R
import com.example.workmanager.receiver.ConnectivityStatusReceiver
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var networkReceiver: ConnectivityStatusReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startServiceBtn.setOnClickListener { startConnectivityService() }
        stopServiceBtn.setOnClickListener { stopConnectivityService() }
    }

    private fun startConnectivityService() {
        val intent = IntentFilter()
        intent.addAction(WifiManager.RSSI_CHANGED_ACTION)

        networkReceiver = ConnectivityStatusReceiver()
        registerReceiver(networkReceiver, intent)
    }

    private fun stopConnectivityService() {
        networkReceiver?.let {
            unregisterReceiver(networkReceiver)
        }
    }
}
