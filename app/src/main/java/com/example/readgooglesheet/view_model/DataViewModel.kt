package com.example.readgooglesheet.view_model

import androidx.lifecycle.ViewModel
import com.example.readgooglesheet.network.Repository

class DataViewModel : ViewModel() {
    private var repository = Repository()
    var data = repository.response

    fun getDataList() {
        repository.getResponse()
    }
}