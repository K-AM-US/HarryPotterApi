package com.kamus.harrypotterapi.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.kamus.harrypotterapi.databinding.ActivityStaffViewBinding
import com.kamus.harrypotterapi.model.Student
import com.kamus.harrypotterapi.network.HPApi
import com.kamus.harrypotterapi.network.RetrofitService
import com.kamus.harrypotterapi.utils.Constants
import com.kamus.harrypotterapi.view.adapters.StaffAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StaffView : AppCompatActivity() {

    private lateinit var binding: ActivityStaffViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStaffViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.errorMessage.visibility = View.GONE
        binding.buttonError.visibility = View.GONE
        binding.buttonError.performClick()
    }
    private fun staffClick(student: Student) {
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

        val intent = Intent(this, StaffDetail::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    fun reload(view: View){

        binding.errorMessage. visibility = View.GONE
        binding.buttonError.visibility = View.GONE
        binding.nimbusBar.visibility = View.VISIBLE

        val call = RetrofitService.getRetrofit().create(HPApi::class.java)
            .getCharacters("api/characters/staff")

        call.enqueue(object : Callback<ArrayList<Student>>{
            override fun onResponse(
                call: Call<ArrayList<Student>>,
                response: Response<ArrayList<Student>>
            ) {
                binding.nimbusBar.visibility = View.GONE

                Log.d(Constants.LOGTAG, "Respuesta del servidor: ${response.body().toString()} ")

                binding.rvStaff.layoutManager = LinearLayoutManager(this@StaffView)
                binding.rvStaff.adapter = StaffAdapter(
                    this@StaffView,
                    response.body()!!
                ) { selectedStaff: Student -> staffClick(selectedStaff)}
            }

            override fun onFailure(call: Call<ArrayList<Student>>, t: Throwable) {
                binding.nimbusBar.visibility = View.GONE
                binding.errorMessage.visibility = View.VISIBLE
                binding.buttonError.visibility = View.VISIBLE
                Toast.makeText(this@StaffView, "No hay conexión", Toast.LENGTH_SHORT).show()
            }
        })
    }
}