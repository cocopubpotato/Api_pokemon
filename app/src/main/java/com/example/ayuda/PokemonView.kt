package com.example.ayuda

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

//Paso 6

//VISTA 2
//El listado de los pokemones del habitat

@Composable
fun PokemonView(navegador: NavHostController, viewModel: PokenModel) { //llamamos al viewmodel que hicimos previamente
    Column(Modifier.fillMaxSize().padding(20.dp))
    {
        Text("Bienvenido al mini pokedex", fontSize = 25.sp, textAlign = TextAlign.Center)

        //estos 2 textos cambian cuando se presione el boton
        Row() {//nombre del habitat seleccionado
            Text("El habitat es ", fontSize = 15.sp, textAlign = TextAlign.Center)
            Text(viewModel.habitatname, fontWeight = FontWeight.Bold)
            Text(text="Size: ${viewModel.pokemonlist.size}", fontSize = 10.sp, textAlign = TextAlign.End,modifier=Modifier.fillMaxWidth())  //cantidad de elementos en la lista

        }

        Row() {
            Button(onClick = {
                Log.d("TEST", "Botón aleatorio presionado")
                viewModel.loadRandom() //cargamos la funcion de cargar los datos
            }) {
                Text("Habitat aleatorio")
            }
            Button(onClick = {navegador.popBackStack()}) {Text("Return") }  //boton de retorno
        }


        //al ser multiples datos podemos usar una lazy column
        /*
        LazyColumn(Modifier.fillMaxWidth()) {
            items(viewModel.pokemonlist) { pokemon ->   //llamamos la variable que almacena el listado
                Text(pokemon.name,Modifier.padding(3.dp).align(Alignment.CenterHorizontally))  //muestra los nombres de los pokemones
            }
        }
        */

        //son tantos datos que es un poco mejor la grid
        LazyVerticalGrid(GridCells.Fixed(2)) {
            items(viewModel.pokemonlist) { pokemon ->   //llamamos la variable que almacena el listado
                Card(Modifier
                    .padding(4.dp)
                    .clickable{
                        viewModel.loadPokenDeat(pokemon.name) //para que cargue la info del pokemon seleccionado
                        navegador.navigate("detail")}
                ) {
                    Text(pokemon.name,Modifier.padding(3.dp).align(Alignment.CenterHorizontally))  //muestra los nombres de los pokemones
                }

            }
        }



    }
}