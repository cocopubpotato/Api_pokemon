package com.example.ayuda

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

//PASO 2

//aqui es de donde sacas la ruta especifica
interface interfase {
    @GET("pokemon-habitat/{id}/")
    suspend fun getHabitat(
            @Path("id") id: Int  //como saque lista de datos ocupaba un path extra en la ruta
    ): Response<datoHabitat>

    /*
    esta seria si solo quisiera sacar un dato sencillo

    @GET("pokemon-habitat)
    suspend fun getHabitat(): Response<datoHabitat>
     */


}