package com.example.ayuda

import android.util.Log


//PASO 4

//aqui es donde se realizan las funciones para recuperar datos del api
class API {
    private val service = RetroFitHelper.getRetrofitService() //verificamos que se reciban los datos

    /*
    si solo quisieras pasar los datos de la lista esto funciona

    suspend fun getPokenlist(id: Int): List<datopoken> {
        val response = service.getHabitat(id)
        if (response.isSuccessful) {
            val body = response.body()
            Log.d("Test", "si recibo dato, ${body.toString()}")
            return body?.pokemon_species ?: emptyList()
        }
        return emptyList()
    }
    */


    //pero como queria recibir el nombre del habitat pos recolectamos todos los datos
    suspend fun getHabitatFull(id: Int):datoHabitat?{  //ya que son los datos de un habitat especifico requerimos la id de este
        val response= service.getHabitat(id)
        return if (response.isSuccessful)response.body() else null
    }
}