package com.example.readgooglesheet.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class ResponseModel{
    @SerializedName("error")
    @Expose
    var error: String? = null

    @SerializedName("status")
    @Expose
    var status: Int? = null

    constructor() {}
    constructor(error: String?, status: Int?) {
        this.error = error
        this.status = status
    }
}


open class DefaultResponseModel {
    @SerializedName("error")
    @Expose
    var error: String? = null

    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("message")
    @Expose
    var message: String? = null

    @SerializedName("status_code")
    @Expose
    var statusCode: String? = null

    constructor() {}
    constructor(error: String?, status: String?) {
        this.error = error
        this.status = status
    }
}