package com.kamus.harrypotterapi.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kamus.harrypotterapi.R
import com.kamus.harrypotterapi.databinding.StaffElementBinding
import com.kamus.harrypotterapi.model.Student

class StaffAdapter(
    private var context: Context,
    private var staff: ArrayList<Student>,
    private val clickListener: (Student) -> Unit
) : RecyclerView.Adapter<StaffAdapter.ViewHolder>() {

    class ViewHolder(view: StaffElementBinding) : RecyclerView.ViewHolder(view.root) {
        val ivStaff = view.ivStaff
        val tvName = view.name
        val tvActor = view.actor
        val tvHouse = view.house
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = StaffElementBinding.inflate(LayoutInflater.from(context))
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = staff.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvName.text = staff[position].name
        holder.tvActor.text = staff[position].actor
        holder.tvHouse.text = staff[position].house

        if (staff[position].img == "") {
            Glide.with(context)
                .load(R.drawable.avatarhp)
                .into(holder.ivStaff)
        } else {
            Glide.with(context)
                .load(staff[position].img)
                .into(holder.ivStaff)
        }
        holder.itemView.setOnClickListener {
            clickListener(staff[position])
        }
    }
}