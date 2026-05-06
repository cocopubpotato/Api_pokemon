package com.example.ayuda

import android.util.Log

class API {
    private val service = RetroFitHelper.getRetrofitService() //verificamos que se reciban los datos

    suspend fun getPokenlist(id: Int): List<datopoken> {
        val response = service.getHabitat(id)

        if (response.isSuccessful) {
            val body = response.body()
            Log.d("Test", "si recibo dato, ${body.toString()}")
            return body?.pokemon_species ?: emptyList()
        }
        return emptyList()
    }


    suspend fun getHabitatFull(id: Int):datoHabitat?{
        val response= service.getHabitat(id)
        return if (response.isSuccessful)response.body() else null
    }
}