package com.example.ayuda

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

//Paso 6
//VISTA 1
//listado de los diferentes habitats que hay

@Composable
fun HabitatView(navegador: NavHostController, viewModel: PokenModel) {
    LaunchedEffect(Unit) {viewModel.loadHabitats() }// Carga al iniciar la vista el listado de los habitats

    Column(Modifier
            .fillMaxSize()
            .padding(top = 20.dp)
    ) {

        Text("Bienvenido al mini pokedex", fontSize = 25.sp, textAlign = TextAlign.Center)
        Text("Escoge el habitat\n", fontSize = 15.sp, textAlign = TextAlign.Center)

        Button(onClick = {
            Log.d("TEST", "Botón aleatorio presionado")
            viewModel.loadRandom()  //si no lo pones solo te transporta a la pestaña pero tienes que volver a presionar el boton
            navegador.navigate("pokemon") //cargamos la funcion de cargar los datos
        },Modifier.align(Alignment.CenterHorizontally)) {
            Text("Habitat aleatorio")
        }


        LazyColumn(Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally ) {
            items(viewModel.habitats) { habitat ->   //llamamos la variable que almacena el listado
                Card(Modifier
                    .padding(4.dp)
                    .clickable{
                        val id= habitat.url     //busca la id del habitat
                        .split("/").last { it.isNotEmpty() }
                        .toInt()
                        viewModel.loadPokemon(id)       //tomando en base la id busca la lista de esos pokemones
                        navegador.navigate("pokemon")}
                ) {
                    Text(habitat.name,Modifier.padding(3.dp).align(Alignment.CenterHorizontally))  //nombre del habitat seleccionado
                }

            }
        }
    }
}