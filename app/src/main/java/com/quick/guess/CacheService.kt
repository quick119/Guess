package com.quick.guess

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class CacheService : Service() {
    private val TAG = CacheService::class.java.simpleName

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand")
        return START_STICKY
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        super.onDestroy()
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

}