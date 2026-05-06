package com.example.ayuda

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage

//PASO 6

//la interfaz de usuario

@Composable
fun PokemonView(viewModel: PokenModel) { //llamamos al viewmodel que hicimos previamente
    Column(Modifier.fillMaxSize().padding(15.dp))
    {
        Text("Bienvenido al mini pokedex", fontSize = 20.sp, textAlign = TextAlign.Center)
        Text("Escoge el habitat\n", fontSize = 15.sp, textAlign = TextAlign.Center)

        Button(onClick = {
            Log.d("TEST", "Botón presionado")
            viewModel.loadPokemon() //cargamos la funcion de cargar los datos
        },Modifier.align(Alignment.CenterHorizontally)) {
            Text("traer datos")
        }


        //estos 2 textos cambian cuando se presione el boton
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
            Text("Size: ${viewModel.pokemonlist.size}", fontSize = 15.sp)  //cantidad de elementos en la lista
            Text("Habitat Seleccionado: ${viewModel.habitatname}", fontSize = 18.sp) //nombre del habitat seleccionado

        }

        //al ser multiples datos podemos usar una lazy column
        LazyColumn(Modifier.fillMaxWidth()) {
            items(viewModel.pokemonlist) { pokemon ->   //llamamos la variable que almacena el listado
                Text(pokemon.name,Modifier.padding(3.dp).align(Alignment.CenterHorizontally))  //muestra los nombres de los pokemones
            }
        }
    }
}