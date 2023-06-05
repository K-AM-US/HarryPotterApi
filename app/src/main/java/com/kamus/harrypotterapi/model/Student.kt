package com.kamus.harrypotterapi.model

import com.google.gson.annotations.SerializedName

data class Student(
    @SerializedName("name")
    var name: String,

    @SerializedName("actor")
    var actor: String,

    @SerializedName("house")
    var house: String,

    @SerializedName("image")
    var img: String,

    @SerializedName("species")
    var species: String,

    @SerializedName("gender")
    var gender: String,

    @SerializedName("wand")
    var wand: Wand,

    @SerializedName("dateOfBirth")
    var birth: String,

    @SerializedName("patronus")
    var patronus: String
)
data class Wand (
    @SerializedName("core")
    var core: String
)