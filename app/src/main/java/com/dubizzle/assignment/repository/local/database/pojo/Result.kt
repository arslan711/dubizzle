package com.dubizzle.assignment.repository.local.pojo

import com.dubizzle.assignment.repository.local.database.pojo.Dubizzle
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Result(


    @SerializedName("results")
    @Expose
    val dubizzles: List<Dubizzle>
)
