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
//tengan cuidado de que las funciones no esten apiladas porque tons no funcionan
class PokenModel: ViewModel() {

    //estas dos son corrutinas
    var pokemonlist= mutableStateListOf<datopoken>()  //donde se almacenan los datos de la lista para visualizarlos
        private set
    var habitatname by mutableStateOf("") //almacenamos el nombre del habitat a seleccionar
        private set
    var habitats = mutableStateListOf<HabitatItem>() //listado de todos los habitats
        private set

    var pokenDetail by mutableStateOf<PokemonDetails?>(null)//detallitos poken
        private set

    fun loadPokemon(Id: Int){  //para cargar los datos
        viewModelScope.launch {
            val habitat = API().getHabitatFull(Id) // funcion que establecimos en archivo API     ,para que pueda conseguir los datos completos del habitat seleccionado
            habitatname= habitat?.name ?: "desconocido"  //almacenamos el dato del habitat actual y colocamos en caso de fallos
            Log.d("TEST", "agregado listado de pokemones y habitats")// prueba de que este funcional , se muestra en el logcat

            pokemonlist.clear() //como la lista se actualiza cada que se presiona el boton pos la tenemos que limpiar
            pokemonlist.addAll(habitat?.pokemon_species ?: emptyList()) //almacenamos la lista de los pokemones del habitat actual

        }
    }

    fun loadRandom() { //cargar un dato aleatorio
        viewModelScope.launch {
            val randomId = Random.nextInt(1, 10)//1 al 9  , para sacar aleatoriamente el habitat
            loadPokemon(randomId)
        }

    }
    fun loadHabitats() { //lista individual de habitats
        viewModelScope.launch {

            val data =
                API().getHabitats() // funcion que establecimos en archivo API     ,para que pueda conseguir los datos completos del habitat seleccionado

            Log.d(
                "TEST",
                "agregado habitat individual"
            )// prueba de que este funcional , se muestra en el logcat

            habitats.clear() //como la lista se actualiza cada que se presiona el boton pos la tenemos que limpiar
            habitats.addAll(data) //almacenamos la lista de los pokemones del habitat actual

        }
    }

    //es solo los detalles del pokemon seleccionado
    fun loadPokenDeat(name: String){    //toda la info del pokemon seleccionado
        viewModelScope.launch { pokenDetail= API().getPokenDetail(name) }

    }
}