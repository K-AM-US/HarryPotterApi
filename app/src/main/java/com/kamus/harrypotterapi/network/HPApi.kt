package com.kamus.harrypotterapi.network

import com.kamus.harrypotterapi.model.Student
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface HPApi {
    @GET
    fun getCharacters(
        @Url url: String? = null
    ):Call<ArrayList<Student>>
}