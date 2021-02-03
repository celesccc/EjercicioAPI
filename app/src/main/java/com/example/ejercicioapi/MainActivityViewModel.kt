package com.example.ejercicioapi

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class MainActivityViewModel : ViewModel() {

    suspend fun getApiResults() : List <String> {
        return withContext(Dispatchers.IO) {
            val resultado = GlobalScope.async {
                DownloadManager.downloadApiResults()
            }
            resultado.await()
        }
    }
}