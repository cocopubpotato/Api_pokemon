package com.example.ayuda

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//PASO 1
object RetroFitHelper {
    private val URL_BASE = "https://pokeapi.co/api/v2/"
    //direccion base del api
    fun getRetrofitService(): interfase{//interfase == interfase api
        val retrofit = Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service: interfase= retrofit.create(interfase::class.java)
        return service
    }
//toda esta funcion se queda asi como esta lo unico que puede cambiar es el nombre de tu interfase api


}