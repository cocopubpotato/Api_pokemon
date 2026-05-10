package com.example.ayuda

import java.sql.Types


//PASO 3
//para los que anden googleando son los endpoints porque es donde termina la informacion :P
//aqui es donde estableces que datos vas a recibir de la ruta

//poner los nombres exactos que vienen en el json del api para las variables
data class datoHabitat( //datos del habitat
    val name: String,
    val pokemon_species: List<datopoken>
)

data class datopoken( //datos del pokemon
    val name:String,
    val url: String   //el url no es una imagen, es un simple link a el resto de sus datos
)

data class HabitatListResponse(  //el listado de los habitats
    val results: List<HabitatItem>
)
data class HabitatItem( //el puro habitat
    val name: String,
    val url: String
)

//DETALLES  de los pokemones

data class PokemonDetails(   //mostrado de todos los datos
    val name: String,
    val height: Int,
    val weight: Int,
    val id: Int,
    val sprites: SpriteData,
    val types: List<TypeSlot>,       //esta en la documentacion que es una lista de datos, porque pueden ser de dos tipos
    val stats: List<StatSlot>
)

data class SpriteData(   //colecta de los sprites
    val front_default: String               //vienen desde git tonces esta raro eso
)

data class TypeSlot( //colecta del tipo
    val type: TypeData
)

data class TypeData(
    val  name: String
)

data class StatSlot(
    val base_stat: Int,     //valor del stat
    val stat: Stat      //nombre, como son varios pos ahi va otra lista
)
data class Stat(
    val name: String
)