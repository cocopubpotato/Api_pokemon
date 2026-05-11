package com.example.ayuda

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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

//Vista para listado de pokemones favoritos
@Composable
fun FavoritoView(navegador: NavHostController, vm: PokenModel) {


    //info previa del usuario
    val context = LocalContext.current
    val tpreferences= trainer(contexto = context)
    val nombre = tpreferences.name.collectAsState("")

    val favs   = vm.favoritos       //el listado de los favoritos

    Column(Modifier
        .fillMaxSize()
        .padding(top = 20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text("Bienvenido al mini pokedex", fontSize = 25.sp, textAlign = TextAlign.Center)
        Text("Hola ${nombre.value}")
        Text("Pokens Favoritos", fontSize = 15.sp, textAlign = TextAlign.Center)
        Button(onClick = { navegador.popBackStack() }) { Text("Return") }  //boton de retorno

        if (favs.isEmpty()){        //se verifica que no venga vacio
            Text("Agrega tus pokemons favoritos a la lista :)")
        }else {
            LazyColumn(             //se repite por cada pokemon almacenado
                Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(favs) { poke ->   //llamamos la variable que almacena el listado
                    Card(
                        Modifier
                            .padding(4.dp)
                            .clickable {
                                vm.loadPokemon(poke.id)  //tomando en base la id busca la lista de esos pokemones
                                navegador.navigate("pokemon")
                            }
                    ) {
                        Text(
                            "Nombre: ${poke.name}",
                            Modifier.padding(3.dp).align(Alignment.CenterHorizontally)
                        )  //nombre del pokemon agregado
                        Text("Numero: ${poke.id}")

                    }
                }
            }
        }
    }
}