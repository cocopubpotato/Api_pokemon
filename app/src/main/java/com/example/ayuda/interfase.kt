package com.example.ayuda

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryName

//PASO 2

//aqui es de donde sacas la ruta especifica
interface interfase {
    @GET("pokemon-habitat/{id}/")  //listado de los pokemones en un habitat
    suspend fun getHabitat(
            @Path("id") id: Int  //como saque lista de datos ocupaba un path extra en la ruta
    ): Response<datoHabitat>



    @GET("pokemon-habitat/")        //listado de los habitats
    suspend fun getHabitats():
            Response<HabitatListResponse>


    @GET("pokemon/{name}")  //detalles de un pokemon
    suspend fun getPokenDetails(
        @Path("name")name: String
    ): Response<PokemonDetails>
}