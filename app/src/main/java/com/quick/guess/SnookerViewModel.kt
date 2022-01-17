package com.quick.guess

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.quick.guess.data.Event
import com.quick.guess.data.EventResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL

class SnookerViewModel: ViewModel() {
    val events = MutableLiveData<List<Event>>()
    init {
        viewModelScope.launch(Dispatchers.IO) {
            val data = URL("http://api.snooker.org/?t=5&s=2020").readText()
            events.postValue(Gson().fromJson(data, EventResult::class.java))
        }
    }
}