package com.kamus.harrypotterapi.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.kamus.harrypotterapi.R
import com.kamus.harrypotterapi.databinding.ActivityStaffDetailBinding
import com.kamus.harrypotterapi.databinding.ActivityStudentDetailBinding
import com.kamus.harrypotterapi.databinding.StaffElementBinding

class StaffDetail : AppCompatActivity() {

    private lateinit var binding: ActivityStaffDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStaffDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras

        if (bundle != null) {
            binding.name.text = bundle.getString("name", "Sin información")
            binding.birth.text = bundle.getString("birth", "Sin información")
            binding.species.text = bundle.getString("species", "Sin información")
            binding.wandCore.text = bundle.getString("wandCore", "Sin información")
            binding.patronus.text = bundle.getString("patronus", "Sin información")
            binding.gender.text = bundle.getString("gender", "Sin información")


            if(bundle.getString("image", "") == ""){
                Glide.with(this)
                    .load(R.drawable.avatarhp)
                    .into(binding.roundedImageView)
            }else {
                Glide.with(this)
                    .load(bundle.getString("image", ""))
                    .into(binding.roundedImageView)
            }

            val house = when(bundle.getString("house", "")){
                "Gryffindor" -> R.drawable.gryffindor
                "Slytherin" -> R.drawable.slytherin
                "Hufflepuff" -> R.drawable.hufflepuff
                "Ravenclaw" -> R.drawable.ravenclaw
                else -> R.drawable.hw_logo
            }
            binding.houseLogo.setImageResource(house)
        }
    }
}