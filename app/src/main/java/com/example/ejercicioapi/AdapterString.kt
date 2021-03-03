package com.example.ejercicioapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AdapterString : RecyclerView.Adapter<AdapterString.StringViewHolder> () {

    private var listaDatos : List<Makeup>? = null
    private lateinit var recyclerView: RecyclerView

    class StringViewHolder(var root: View, val textView: TextView, val imageView: ImageView) : RecyclerView.ViewHolder(root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_main_item, parent, false)
        val textView = view.findViewById<TextView>(R.id.tv_activity_main_item)
        val imageView = view.findViewById<ImageView>(R.id.iv_image_main)
        return StringViewHolder(view, textView, imageView)
    }

    override fun onBindViewHolder(holder: StringViewHolder, position: Int) {
        listaDatos?.let {
            holder.textView.text = it[position].name
            Picasso.get().load(it[position].imageLink).into(holder.imageView)

            holder.root.setOnClickListener {                        // dónde se ha realizado la pulsación
                val pos = recyclerView.getChildLayoutPosition(it)   // a qué elemento de la lista le corresponde
                listaDatos?.get(pos)?.let { i ->
                    DetailsActivity.createDetailsActivity(holder.root.context, i.toString())
                }
            }
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun getItemCount(): Int {
        listaDatos?.let {
            return it.size
        }
        return 0
    }

    suspend fun setData(string: List<Makeup>) {
        listaDatos = string
        withContext(Dispatchers.Main) {
            notifyDataSetChanged()
        }
    }
}