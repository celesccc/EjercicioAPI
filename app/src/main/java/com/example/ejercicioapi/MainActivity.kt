package com.example.ejercicioapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ejercicioapi.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var model : MainActivityViewModel
    private lateinit var binding: ActivityMainBinding

    private var adapter = AdapterString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("MiTAG", "onCreate")
        Log.e("MiTAG", "onCreate")
        Log.w("MiTAG2", "onCreate")
        Log.i("MiTAG3", "onCreate")
        Log.v("MiTAG4", "onCreate ")

        model = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        createRecyclerView()

        //Log.d("MiPRUEBA", "${createRecyclerView()}")

        GlobalScope.launch (Dispatchers.IO) {
            val resultados = model.getApiResults()
            adapter.setData(resultados)
            hideProgressBar()
        }

        binding.bSiguiente.setOnClickListener {
            DetailsActivity.createDetailsActivity(this)
        }
    }

    private fun createRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private suspend fun hideProgressBar() = withContext(Dispatchers.Main) {
        binding.progressBar2.visibility = View.GONE
    }
}

