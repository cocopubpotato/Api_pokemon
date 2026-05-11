package com.example.ayuda

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController


//Paso 6
//pagina
@Composable
fun InicioView(navegador: NavHostController, viewModel: PokenModel) {
    var busqueda by remember { mutableStateOf("") }

    Column(Modifier
        .fillMaxSize()
        .padding(top = 20.dp).padding(15.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Bienvenido al mini pokedex", fontSize = 25.sp, textAlign = TextAlign.Center)
        Button(onClick = {
            navegador.navigate("Habitat") //cargamos la funcion de cargar los datos
        },Modifier.align(Alignment.CenterHorizontally)) {
            Text("Busqueda por Habitat")
        }
        Button(onClick = {navegador.navigate("favorito")}) {Text("Favoritos") }


        OutlinedTextField(
            value = busqueda,
            onValueChange = { busqueda = it },
            label = { Text("Buscar Pokemon") },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        Button(onClick = {
            viewModel.loadPokenDeat(busqueda)
            navegador.navigate("detail")

        }) {
            Text("Buscar Pokemon")
        }
    }
}