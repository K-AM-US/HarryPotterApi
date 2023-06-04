package com.kamus.harrypotterapi.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.kamus.harrypotterapi.R
import com.kamus.harrypotterapi.databinding.ActivityStudentsViewBinding
import com.kamus.harrypotterapi.model.Student
import com.kamus.harrypotterapi.network.HPApi
import com.kamus.harrypotterapi.network.RetrofitService
import com.kamus.harrypotterapi.utils.Constants
import com.kamus.harrypotterapi.view.adapters.StudentsAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StudentsView : AppCompatActivity() {

    private lateinit var binding: ActivityStudentsViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentsViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val call = RetrofitService.getRetrofit().create(HPApi::class.java)
            .getCharacters("api/characters/students")

        call.enqueue(object: Callback<ArrayList<Student>>{
            override fun onResponse(
                call: Call<ArrayList<Student>>,
                response: Response<ArrayList<Student>>
            ) {
                binding.nimbusBar.visibility = View.GONE

                Log.d(Constants.LOGTAG, "Respuesta del servidor: ${response.body().toString()   } ")

                binding.rvStudent.layoutManager = LinearLayoutManager(this@StudentsView)
                binding.rvStudent.adapter = StudentsAdapter(this@StudentsView, response.body()!!)
            }

            override fun onFailure(call: Call<ArrayList<Student>>, t: Throwable) {
                binding.nimbusBar.visibility = View.GONE
                Toast.makeText(this@StudentsView, "No hay conexión", Toast.LENGTH_SHORT).show()

            }

        })
    }
}