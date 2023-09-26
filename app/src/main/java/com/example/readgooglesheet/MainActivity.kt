package com.example.readgooglesheet

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.readgooglesheet.view_model.DataViewModel
import org.json.JSONArray
import org.json.JSONObject
import java.util.stream.IntStream


class MainActivity : AppCompatActivity() {
    private val apiKey = "AIzaSyDePKu7ySEVujMQlb5FILuXFVnarxV6MRE"
    private val sheetId = "1ZnmDBxiG9T_tMjn3G1nYCeB-R10i-5CVdB3q4oB1Vg0"

    private lateinit var viewModel: DataViewModel
    val url = "https://sheets.googleapis.com/v4/spreadsheets/$sheetId/values/dev?key=$apiKey"

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
                Log.d("test", it?.get(1).toString())
                MyApplication.getMyApplicationInstance().sharedPreferences.setData(
                    "key",
                    it?.get(1).toString())
            }
        })

        /*val queue = Volley.newRequestQueue(this)

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->

                val jObject = JSONObject(response)
                val jArray: JSONArray = jObject.getJSONArray("values")

                IntStream.range(0, jArray.length()).forEach {
                    val array: JSONArray = jArray.getJSONArray(it)

                    if (!array.getString(1).equals("name")) {
                        name = array.getString(1)
                        MyApplication.getMyApplicationInstance().sharedPreferences.clearSharedPreference()
                        MyApplication.getMyApplicationInstance().sharedPreferences.setData(
                            "key",
                            name
                        )
                    }

                }
            },
            {

                Log.d("test", "That didn't work!")
            })
        queue.add(stringRequest)*/

        val mData = MyApplication.getMyApplicationInstance().sharedPreferences.getData("key", "")
        textView.text = mData
        Log.d("test", "Response is: $mData")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SimpleActivity","onDestroy")
    }

}