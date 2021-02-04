package com.example.ejercicioapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ejercicioapi.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AdapterString : RecyclerView.Adapter<AdapterString.StringViewHolder> () {

    private var listaDatos : List<Makeup>? = null
    //private lateinit var binding: ActivityMainBinding

    class StringViewHolder(root: View, val textView: TextView) : RecyclerView.ViewHolder(root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_main_item, parent, false)
        val textView = view.findViewById<TextView>(R.id.tv_activity_main_item)
        return StringViewHolder(view, textView)
    }

    override fun onBindViewHolder(holder: StringViewHolder, position: Int) {
        listaDatos?.let {
            holder.textView.text = it[position].name
        }
    }

    override fun getItemCount(): Int {
        listaDatos?.let {
            return it.size
        }
        return 0
    }

    suspend fun setData(string : List<Makeup>) {
        listaDatos = string
        withContext(Dispatchers.Main) {
            notifyDataSetChanged()
        }
    }


}