package com.example.readgooglesheet.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.readgooglesheet.MyApplication
import com.example.readgooglesheet.model.DataModel
import com.example.readgooglesheet.model.DataResource

class Repository {

    private var apiInterface: ApiInterface? = null

    init {
        apiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)
    }

    private val _getResponse: MutableLiveData<DataResource<DataModel>> =
        MutableLiveData<DataResource<DataModel>>()
    var response: LiveData<DataResource<DataModel>> = _getResponse

    fun getResponse() {
        val call: retrofit2.Call<DataModel>? = apiInterface?.fetchData()
        _getResponse.value = DataResource.loading()
        call?.enqueue(object : retrofit2.Callback<DataModel?> {
            override fun onResponse(
                call: retrofit2.Call<DataModel?>,
                response: retrofit2.Response<DataModel?>
            ) {
                if (response.isSuccessful) {
                    MyApplication.getMyApplicationInstance().sharedPreferences.clearSharedPreference()
                    _getResponse.value = DataResource.success(response.body()!!)
                    Log.d("SimpleActivity","Hello Started")
                } else {
                    _getResponse.setValue(DataResource.error(response.message()))
                }
            }

            override fun onFailure(call: retrofit2.Call<DataModel?>, t: Throwable) {
                _getResponse.value = DataResource.error("Connection failed")
            }
        })
    }

}