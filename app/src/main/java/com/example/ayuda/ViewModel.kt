package com.example.ayuda

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlin.random.Random

//PASO 5


//Establecemos el view model que usaremos en la interfaz de usuario

class PokenModel: ViewModel() {

    //estas dos son corrutinas
    var pokemonlist= mutableStateListOf<datopoken>()  //donde se almacenan los datos de la lista para visualizarlos
        private set
    var habitatname by mutableStateOf("") //almacenamos el nombre del habitat a seleccionar
        private set


    fun loadPokemon(){
        viewModelScope.launch {
            val randomId= Random.nextInt(1,10)//1 al 9  , para sacar aleatoriamente el habitat
            val habitat = API().getHabitatFull(randomId) // funcion que establecimos en archivo API     ,para que pueda conseguir los datos completos del habitat seleccionado


            habitatname= habitat?.name ?: "desconocido"  //almacenamos el dato del habitat actual y colocamos en caso de fallos
            Log.d("TEST", "agregado")// prueba de que este funcional , se muestra en el logcat

            pokemonlist.clear() //como la lista se actualiza cada que se presiona el boton pos la tenemos que limpiar
            pokemonlist.addAll(habitat?.pokemon_species ?: emptyList()) //almacenamos la lista de los pokemones del habitat actual

        }
    }
}