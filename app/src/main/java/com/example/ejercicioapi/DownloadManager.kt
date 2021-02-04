package com.example.ejercicioapi

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class DownloadManager {

    companion object {
        suspend fun downloadApiResults() : List<Makeup> {
            // Conexión a Internet
            val client = OkHttpClient()
            val url = "http://makeup-api.herokuapp.com/api/v1/products.json?brand=maybelline"
            val request = Request.Builder()
                    .url(url)
                    .build()
            val call = client.newCall(request).execute()

            val bodyInString = call.body?.string()
            bodyInString?.let {

                val results = JSONArray(it)
                results?.let {
                    val gson = Gson()

                    val itemType = object : TypeToken<List<Makeup>>() {}.type

                    val list = gson.fromJson<List<Makeup>>(it.toString(), itemType)
                    return list
                }
            }
            /*call.enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()

                }

                override fun onResponse(call: Call, response: Response) {
                    CoroutineScope(Dispatchers.IO).launch {
                        val bodyInString = response.body?.string()
                        bodyInString?.let {
                            val JsonObject = JSONObject(bodyInString)

                            val results = JsonObject.optJSONArray("results")
                            results?.let {
                                val gson = Gson()
                                val itemType = object : TypeToken<List<Makeup>>() {}.type
                                val list = gson.fromJson<List<Makeup>>(results.toString(), itemType)

                            }
                        }
                    }
                }

            })*/

            delay(3000)
            return listOf()
        }

        suspend fun downloadApiSingleResult(userChoise : String) : String {
            // hago la conexión a Internet - OKHTTP ...
            delay(3000)
            return when (userChoise.length) {
                1 -> "UNO"
                2 -> "DOS"
                else -> "OTHERSS"
            }
        }
        }

}