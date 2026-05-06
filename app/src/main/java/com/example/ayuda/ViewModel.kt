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


class PokenModel: ViewModel() {



    //mostrar la lista de los pokemons en el habitat seleccionado
    var pokemonlist= mutableStateListOf<datopoken>()
        private set
    var habitatname by mutableStateOf("")
        private set

    fun loadPokemon(){
        viewModelScope.launch {
            val randomId= Random.nextInt(1,10)//1 al 9
            val habitat = API().getHabitatFull(randomId) //para que pueda decir que habitat es el que salio
            habitatname= habitat?.name ?: "desconocido"
            Log.d("TEST", "agregado")
            pokemonlist.clear()
            pokemonlist.addAll(habitat?.pokemon_species ?: emptyList())
        }
    }
}