package com.example.kotlin_mvvm_museum.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin_mvvm_museum.R
import com.example.kotlin_mvvm_museum.model.Museum
import kotlinx.android.synthetic.main.row_museum.view.*

class MuseumAdapter(private var museums: List<Museum>) : RecyclerView.Adapter<MuseumAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_museum, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(museums[position])
    }

    override fun getItemCount(): Int {
        return museums.size
    }

    fun update(data: List<Museum>) {
        museums = data
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvName: TextView = view.tvName
        private val imgMuseum: ImageView = view.imgMuseum
        fun bind(museum: Museum) {
            tvName.text = museum.name.capitalize()
            Glide.with(imgMuseum.context).load(museum.photo).into(imgMuseum)
        }
    }

}