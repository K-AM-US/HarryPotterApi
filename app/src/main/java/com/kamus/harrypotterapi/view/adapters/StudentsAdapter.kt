package com.kamus.harrypotterapi.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kamus.harrypotterapi.R
import com.kamus.harrypotterapi.databinding.StudentElementBinding
import com.kamus.harrypotterapi.model.Student

class StudentsAdapter(private var context: Context, private var students: ArrayList<Student>): RecyclerView.Adapter<StudentsAdapter.ViewHolder>() {

    class ViewHolder(view: StudentElementBinding): RecyclerView.ViewHolder(view.root){
        val ivStudent = view.ivStudent
        val tvName = view.name
        val tvActor = view.actor
        val tvHouse = view.house
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var binding = StudentElementBinding.inflate(LayoutInflater.from(context))
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = students.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = students[position].name
        holder.tvActor.text = students[position].actor
        holder.tvHouse.text = students[position].house

        if(students[position].img == ""){
            Glide.with(context)
                .load(R.drawable.avatarhp)
                .into(holder.ivStudent)
        }else {
            Glide.with(context)
                .load(students[position].img)
                .into(holder.ivStudent)
        }
        holder.itemView.setOnClickListener{
            // Programar evento click a todo elemento del view holder
        }
    }
}