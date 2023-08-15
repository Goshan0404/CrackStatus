package com.example.crackstatus.presentation.ioc

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.crackstatus.R

class RecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val message: TextView = itemView.findViewById(R.id.textView)

    fun bind(message: String) {
        this.message.text = message

    }

}