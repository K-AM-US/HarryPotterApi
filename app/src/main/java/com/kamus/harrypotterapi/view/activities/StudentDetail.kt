package com.kamus.harrypotterapi.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.kamus.harrypotterapi.R
import com.kamus.harrypotterapi.databinding.ActivityStudentDetailBinding

class StudentDetail : AppCompatActivity() {

    private lateinit var binding: ActivityStudentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras

        if (bundle != null) {
            binding.name.text = bundle.getString("name", getString(R.string.unknown))
            binding.birth.text = bundle.getString("birth", getString(R.string.unknown))
            binding.species.text = bundle.getString("species", getString(R.string.unknown))
            binding.wandCore.text = bundle.getString("wandCore", getString(R.string.unknown))
            binding.patronus.text = bundle.getString("patronus", getString(R.string.unknown))
            binding.gender.text = bundle.getString("gender", getString(R.string.unknown))


            if(binding.name.text == "")
                binding.name.text = getString(R.string.unknown)
            if(binding.wandCore.text == "")
                binding.wandCore.text = getString(R.string.unknown)
            if(binding.birth.text == "")
                binding.birth.text = getString(R.string.unknown)
            if(binding.patronus.text == "")
                binding.patronus.text = getString(R.string.unknown)
            if(binding.species.text == "")
                binding.species.text = getString(R.string.unknown)
            if(binding.gender.text == "")
                binding.gender.text = getString(R.string.unknown)


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