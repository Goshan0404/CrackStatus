package com.example.crackstatus

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val message: TextView = itemView.findViewById(R.id.textView)

    fun bind(message: String) {
        this.message.text = message

    }

}