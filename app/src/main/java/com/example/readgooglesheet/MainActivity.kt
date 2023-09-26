package com.example.readgooglesheet

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.readgooglesheet.helper.MyApplication
import com.example.readgooglesheet.view_model.DataViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: DataViewModel
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("SimpleActivity","OnCreate Started")
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)

        viewModel = ViewModelProvider(this)[DataViewModel::class.java]
        viewModel.getDataList()


        viewModel.data.observe(this, Observer {data->

            data?.data?.values?.forEach { it ->
                it?.get(1)?.get(1)
                Log.d("SimpleActivity", it?.get(1).toString())
                textView.text = it?.get(1).toString()
            }
        })

        val mData = MyApplication.getMyApplicationInstance().sharedPreferences.getData("key", "")

        Log.d("SimpleActivity", "sharedPreferences is: $mData")
    }

    override fun onResume() {
        super.onResume()
        viewModel.getDataList()
        Log.d("SimpleActivity", "Call Api")
    }

    override fun onStop() {
        MyApplication.getMyApplicationInstance().sharedPreferences.clearSharedPreference()
        Log.d("SimpleActivity", "Data Clear")
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SimpleActivity","onDestroy")
    }

}