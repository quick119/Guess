package com.quick.guess

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.quick.guess.data.GameDatabase
import kotlinx.android.synthetic.main.activity_record_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecordListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record_list)
        //get records
        CoroutineScope(Dispatchers.IO).launch {
            val records = GameDatabase.getInstance(this@RecordListActivity)?.
                recordDao()?.getAll()
            records?.let {
                runOnUiThread {
                    recycler.layoutManager = LinearLayoutManager(this@RecordListActivity)
                    recycler.setHasFixedSize(true)
                    recycler.adapter = RecordAdapter(it)
                }
            }
        }
    }
}