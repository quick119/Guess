package com.quick.guess

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.row_function.view.*

class MainActivity : AppCompatActivity() {
    val TAG = MainActivity::class.java.simpleName
    val functions = listOf<String>(
        "Camera",
        "Guess game",
        "Record list",
        "Download coupons",
        "News",
        "Maps")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //RecyclerView

        recycler.layoutManager = LinearLayoutManager(this)
        recycler.setHasFixedSize(true)
        recycler.adapter = FunctionAdapter()
        //spinner
        val colors = arrayOf("Red", "Green", "Blue")
        val adapter = ArrayAdapter<String>(this,
            android.R.layout.simple_spinner_item, colors)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                Log.d(TAG, "onItemSelected: ${colors[position]}")
            }

        }
    }

    inner class FunctionAdapter() : RecyclerView.Adapter<FunctionHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FunctionHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_function, parent, false)
            val holder = FunctionHolder(view)
            return holder
        }

        override fun getItemCount(): Int {
            return functions.size
        }

        override fun onBindViewHolder(holder: FunctionHolder, position: Int) {
            holder.nameText.text = functions.get(position)
            holder.itemView.setOnClickListener {
                functionClicked(position)
            }
        }
    }

    private fun functionClicked(position: Int) {
        when(position) {
            1 -> startActivity(Intent(this, MaterialActivity::class.java))
            2 -> startActivity(Intent(this, RecordListActivity::class.java))
            else -> return
        }
    }

    class FunctionHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nameText: TextView = view.name
    }
}