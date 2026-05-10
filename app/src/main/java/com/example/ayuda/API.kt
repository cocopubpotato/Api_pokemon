package com.example.ayuda

import kotlin.collections.emptyList


//PASO 4

//aqui es donde se realizan las funciones para recuperar datos del api
class API {
    private val service = RetroFitHelper.getRetrofitService() //verificamos que se reciban los datos

    //si solo quisieras pasar los nombres de los habitats esto funciona
    suspend fun getHabitats(): List<HabitatItem> {
        val response = service.getHabitats()
        return if(response.isSuccessful) {
            response.body()?.results ?: emptyList()
        } else {
            emptyList()
        }
    }


    //pero como queria recibir el nombre del habitat pos recolectamos todos los datos
    suspend fun getHabitatFull(id: Int):datoHabitat?{  //ya que son los datos de un habitat especifico requerimos la id de este
        val response= service.getHabitat(id)
        return if (response.isSuccessful)response.body() else null
    }

    //para recolectar los datos de los pokemonsitos
    suspend fun getPokenDetail(name: String): PokemonDetails?{
        val  response= service.getPokenDetails(name)
        return if (response.isSuccessful){
            response.body()
        }else{
            null
        }
    }
}