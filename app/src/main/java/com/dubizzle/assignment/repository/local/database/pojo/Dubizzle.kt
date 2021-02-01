package com.dubizzle.assignment.repository.local.database.pojo

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@Entity(tableName = "dubizzle_table")
data class Dubizzle(

    @SerializedName("uid")
    @Expose
    @PrimaryKey
    val id: String,


    val name: String,

    val price: String,

    @SerializedName("created_at")
    @Expose
    val createdAtTime: String,

    @SerializedName("image_urls")
    @Expose
    val images: List<String>
)
