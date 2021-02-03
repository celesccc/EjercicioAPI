package com.example.ejercicioapi

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class DetailsActivityViewModel : ViewModel() {

    suspend fun getSingleItem(userChoise : String) : String {
        return withContext(Dispatchers.IO) {
            val resultado = GlobalScope.async {
                DownloadManager.downloadApiSingleResult(userChoise)
            }
            resultado.await()
        }
    }
}
