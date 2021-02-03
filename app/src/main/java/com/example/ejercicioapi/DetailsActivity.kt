package com.example.ejercicioapi

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.ejercicioapi.databinding.ActivityDetailsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var model : DetailsActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = ViewModelProvider(this).get(DetailsActivityViewModel::class.java)

        binding.bBuscar.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            GlobalScope.launch (Dispatchers.IO) {
                val resultado = model.getSingleItem(binding.etDetails.text.toString())

                withContext(Dispatchers.Main) {
                    binding.tvResultados.text = resultado
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }

    companion object {
        fun createDetailsActivity(context : Context) {
            val intent = Intent(context, DetailsActivity::class.java)
            context.startActivity(intent)
        }
    }
}