package com.quick.guess

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.quick.guess.data.GameDatabase
import com.quick.guess.data.Record

import kotlinx.android.synthetic.main.activity_material.*
import kotlinx.android.synthetic.main.content_material.*
import kotlinx.android.synthetic.main.content_material.counter

class MaterialActivity : AppCompatActivity() {
    private lateinit var viewModel: GuessViewModel
    private val REQUEST_RECORD: Int = 100
    val secretNumber = SecretNumber()
    val TAG = MaterialActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: ")
        setContentView(R.layout.activity_material)
        setSupportActionBar(toolbar)
        viewModel = ViewModelProvider(this).get(GuessViewModel::class.java)
        viewModel.counter.observe(this, Observer {data ->
            counter.setText(data.toString())
        })

        fab.setOnClickListener { view ->
            replay()
        }
        counter.setText(secretNumber.count.toString())
        Log.d(TAG, "onCreate:" + secretNumber.secret)
        val count = getSharedPreferences("guess", Context.MODE_PRIVATE)
            .getInt("REC_COUNTER", -1)
        val nick = getSharedPreferences("guess", Context.MODE_PRIVATE)
            .getString("REC_NICKNAME", null)
        Log.d(TAG, "data: $count / $nick")
        //Room read test
        /*AsyncTask.execute {
            val list = GameDatabase.getInstance(this)?.recordDao()?.getAll()
            list?.forEach {
                Log.d(TAG, "record: ${it.nickname} ${it.counter}");
            }
        }*/
    }

    private fun replay() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.replay_game))
            .setMessage(getString(R.string.are_you_sure))
            .setPositiveButton(getString(R.string.ok), { dialog, which ->
                secretNumber.reset()
                Log.d(TAG, "serect:" + secretNumber.secret)
                counter.setText(secretNumber.count.toString())
                ed_number.setText("")
            })
            .setNeutralButton(getString(R.string.cancel), null)
            .show()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause : ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")

    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

    fun check(view : View) {
        viewModel.guess(3)
        /*val n = ed_number.text.toString().toInt()
        println("number: $n")
        Log.d(TAG, "n:" + n)

        var count = (secretNumber.count)
        var diff = (secretNumber.validate(n))
        var message = when {
            diff < 0 -> getString(R.string.bigger)
            diff > 0 -> getString(R.string.smaller)
            count in 0..2 -> getString(R.string.excellent) + n
            else -> getString(R.string.yes_you_got_it)
        }

        counter.setText(secretNumber.count.toString())
//       Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_title))
            .setMessage(message)
            .setPositiveButton(getString(R.string.ok), {dialog, which ->
                if (diff == 0) {
                    val intent = Intent(this, RecordActivity::class.java)
                    intent.putExtra("COUNTER", secretNumber.count)
//                    startActivity(intent)
                    startActivityForResult(intent, REQUEST_RECORD)
                }
            })
            .show()*/
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_RECORD) {
            if (resultCode == Activity.RESULT_OK) {
                val nickname = data?.getStringExtra("NICK")
                Log.d(TAG, "onActivityResult " + nickname)
                replay()
            }
        }
    }
}
