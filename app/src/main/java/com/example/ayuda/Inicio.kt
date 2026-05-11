package com.example.ayuda

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch


//Paso 6
//pagina de inicio , busqueda y registro
@Composable
fun InicioView(navegador: NavHostController, viewModel: PokenModel) {
    var busqueda by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }   //la variable que se va a guardar, tienen que llevar diferente nombre

    //info previa del usuario
    val context = LocalContext.current
    val preferences = trainer(contexto = context)
    val corrutina= rememberCoroutineScope()
    val name = preferences.name.collectAsState("")      //la que se llama para poder ver el dato

    Column(Modifier
        .fillMaxSize()
        .padding(top = 20.dp).padding(15.dp)
        .verticalScroll(rememberScrollState()) ,
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Bienvenido al mini pokedex", fontSize = 25.sp, textAlign = TextAlign.Center)
        Text("Hola ${name.value}")


        Button(onClick = {
            navegador.navigate("Habitat") //cargamos la funcion de cargar los datos
        },Modifier.align(Alignment.CenterHorizontally)) {
            Text("Busqueda por Habitat")
        }
        Button(onClick = {navegador.navigate("favorito")}) {Text("Favoritos") }

        Column () {         //busqueda de pokemon individual
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
                viewModel.loadPokenDeat(busqueda)  //carga la id del pokemon
                Log.d("Test","buscando pokemon")
                navegador.navigate("detail")

            }) {
                Text("Buscar Pokemon")
            }
        }

        Column() {                 //Guardar nombre de usuario
            OutlinedTextField(
                value = nombre,
                onValueChange = {nombre= it},
                label = { Text("colocar nombre") },
                modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp))

            Button(onClick = {
                corrutina.launch {
                    preferences.guardarTrainerData(nombre) }  //almacena la informacion
                Log.d("Test","Nombre asignado")
            }){
                Text("Confirmar nombre")
            }

        }


    }
}