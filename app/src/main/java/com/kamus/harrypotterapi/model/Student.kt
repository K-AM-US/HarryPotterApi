package com.kamus.harrypotterapi.model

import com.google.gson.annotations.SerializedName

data class Student(
    @SerializedName("id")
    var id: String,

    @SerializedName("name")
    var name: String,

    @SerializedName("actor")
    var actor: String,

    @SerializedName("house")
    var house: String,

    @SerializedName("image")
    var img: String
)
