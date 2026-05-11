package com.example.ayuda

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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


@Composable
fun FavoritoView(navegador: NavHostController, viewModel: PokenModel) {


    //info previa del usuario
    val context = LocalContext.current
    val preferences= Favoritos(contexto = context)
    val name = preferences.name.collectAsState("")
    val id = preferences.id.collectAsState(0)

    Column(Modifier
        .fillMaxSize()
        .padding(top = 20.dp)
    ) {
        Text("Bienvenido al mini pokedex", fontSize = 25.sp, textAlign = TextAlign.Center)
        Text("Pokens Favoritos", fontSize = 15.sp, textAlign = TextAlign.Center)
        Button(onClick = { navegador.popBackStack() }) { Text("Return") }  //boton de retorno

        // preferences?.let {  //se verifica que no venga vacio, porque puede que venga nulo y marca error
            LazyColumn(
                Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(id.value) { poke ->   //llamamos la variable que almacena el listado
                    Card(
                        Modifier
                            .padding(4.dp)
                            .clickable {
                                val idd = id.value  //busca la id del habitat
                                viewModel.loadPokemon(idd)  //tomando en base la id busca la lista de esos pokemones
                                navegador.navigate("pokemon")
                            }
                    ) {
                        Text("Nombre: ${name.value}", Modifier.padding(3.dp).align(Alignment.CenterHorizontally))  //nombre del habitat seleccionado
                        Text("Numero: ${id.value}")

                    }
                }
            }
    }
}