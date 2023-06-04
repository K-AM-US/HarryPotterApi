package com.kamus.harrypotterapi.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.kamus.harrypotterapi.databinding.ActivityCharacterMenuBinding

class CharacterMenu : AppCompatActivity() {

    private lateinit var binding: ActivityCharacterMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    fun studentsViewClick(view: View) {
        var intent = Intent(this, StudentsView::class.java)
        startActivity(intent)
    }

    fun staffViewClick(view: View) {
        var intent = Intent(this, StaffView::class.java)
        startActivity(intent)
    }
}