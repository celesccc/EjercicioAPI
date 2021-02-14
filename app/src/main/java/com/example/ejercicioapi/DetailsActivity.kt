package com.example.ejercicioapi

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.ejercicioapi.databinding.ActivityDetailsBinding
import com.squareup.picasso.Picasso
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

        val texto = intent.getStringExtra(TAG)

        model = ViewModelProvider(this).get(DetailsActivityViewModel::class.java)

        if (texto != null) {
            makeups(texto)
        }

        binding.bBuscar.setOnClickListener {
            makeups(binding.etDetails.text.toString())
        }
    }

    fun makeups (producto: String) {
        binding.progressBar.visibility = View.VISIBLE
        GlobalScope.launch (Dispatchers.IO) {
            val resultado = model.getSingleItem(binding.etDetails.text.toString())

            withContext(Dispatchers.Main) {
                if (resultado.isNotEmpty()) {
                    resultado.forEach {
                        //Log.w("cel", it.name)
                        binding.tvResultados.text = "MAQUILLAJE: " + it?.name +
                                "\n\nDESCRIPCIÓN: " + it?.description +
                                "\n\nPRECIO: " + it?.price +"€\n\n"
                        Picasso.get().load(it.imageLink).into(binding.ivImage)
                    }
                } else {
                    Toast.makeText(this@DetailsActivity, "Error. Debe introducir un producto correcto.", Toast.LENGTH_LONG).show()
                    binding.tvResultados.text = ""
                    binding.ivImage.visibility = View.GONE
                }
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    companion object {
        private const val TAG = "TAG"
        private const val TAG2 = "TAG2"

        fun createDetailsActivity(context: Context) {
            val intent = Intent(context, DetailsActivity::class.java)
            context.startActivity(intent)
        }
        fun createDetailsActivity(context : Context, valor: String) {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(TAG, valor)
            context.startActivity(intent)
        }
    }

}