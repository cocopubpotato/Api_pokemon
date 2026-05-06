package com.example.ayuda


//PASO 3

//aqui es donde estableces que datos vas a recibir de la ruta
//poner los nombres exactos que vienen en el json del api
data class datoHabitat( //datos del habitat
    val name: String,
    val pokemon_species: List<datopoken>
)

data class datopoken( //datos del pokemon
    val name:String,
    val url: String   //el url no es una imagen, es un simple link a el resto de sus datos
)