package com.kamus.harrypotterapi.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
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

        call.enqueue(object : Callback<ArrayList<Student>> {
            override fun onResponse(
                call: Call<ArrayList<Student>>,
                response: Response<ArrayList<Student>>
            ) {
                binding.nimbusBar.visibility = View.GONE

                Log.d(Constants.LOGTAG, "Respuesta del servidor: ${response.body().toString()} ")

                binding.rvStudent.layoutManager = LinearLayoutManager(this@StudentsView)
                binding.rvStudent.adapter = StudentsAdapter(
                    this@StudentsView,
                    response.body()!!
                ) { selectedStudent: Student -> studentClick(selectedStudent) }
            }

            override fun onFailure(call: Call<ArrayList<Student>>, t: Throwable) {
                binding.nimbusBar.visibility = View.GONE
                Toast.makeText(this@StudentsView,
                    "No hay conexión", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun studentClick(student: Student) {
        Toast.makeText(this, "Click en: ${student.name}", Toast.LENGTH_SHORT).show()

        val bundle = Bundle()

        bundle.putString("name", student.name)
        bundle.putString("house", student.house)
        bundle.putString("image", student.img)
        bundle.putString("species", student.species)
        bundle.putString("gender", student.gender)
        bundle.putString("wandCore", student.wand.core)
        bundle.putString("birth", student.birth)
        bundle.putString("patronus", student.patronus)

        val intent = Intent(this, StudentDetail::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}