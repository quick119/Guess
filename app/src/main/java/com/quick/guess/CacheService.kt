package com.quick.guess

import android.app.IntentService
import android.content.Intent
import android.os.IBinder
import android.util.Log

class CacheService : IntentService("CacheService") {
    override fun onHandleIntent(intent: Intent?) {
        Log.d(TAG, "onHandleIntent")
        Thread.sleep(5000)
    }

    private val TAG = CacheService::class.java.simpleName

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