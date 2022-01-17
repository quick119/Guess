package com.quick.guess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.quick.guess.data.EventResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.net.URL
import kotlin.coroutines.CoroutineContext

class SnookerActivity : AppCompatActivity(), CoroutineScope {
    companion object {
        val TAG = SnookerActivity::class.java.simpleName
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snooker)
        val viewModel = ViewModelProvider(this).get(SnookerViewModel::class.java)
        viewModel.events.observe(this, Observer { events ->
            Log.d(TAG, "onCreate: ${events.size}")
        })

        /*launch {
            val data = URL("http://api.snooker.org/?t=5&s=2020").readText()
            val events = Gson().fromJson(data, EventResult::class.java)
            events.forEach {
                Log.d(TAG, "onCreate: $it")
            }
        }*/
        /*CoroutineScope(Dispatchers.IO).launch {
            val data = URL("http://api.snooker.org/?t=5&s=2020").readText()
            val events = Gson().fromJson(data, EventResult::class.java)
            events.forEach {
                Log.d(TAG, "onCreate: $it")
            }
        }*/
    }

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.IO
}