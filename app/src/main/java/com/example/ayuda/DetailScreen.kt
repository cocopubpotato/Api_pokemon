package com.example.ayuda

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage

//Paso 6

//VISTA 3
//ES LA VISTA DE LA INFORMACION INDIVIDUAL DEL POKEMON
@Composable
fun DetailScreen(navegador: NavHostController,vm:PokenModel) {
    val selected= vm.pokenDetail  //llamamos los detalles
    val yaAgregado = vm.favoritos.any { it.id == selected?.id }

    Column(Modifier
        .fillMaxSize()
        .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally    ) {


        Column(Modifier.fillMaxWidth().height(100.dp)
        ,horizontalAlignment = Alignment.CenterHorizontally  //o pones esto ahi o lo pones por cada elemento en la columna
        ){
            Row(Modifier.padding(6.dp)) {
                Text("Pokemon ", fontSize = 30.sp)
                Text("Details", fontSize = 30.sp)
            }
            Button(
                onClick = { navegador.popBackStack() })
            { Text("Return") }  //Boton de regreso
        }

        Column(Modifier.fillMaxWidth()) {
            selected?.let {  //se verifica que no venga vacio, porque puede que venga nulo y marca error
                Text("Number: ${selected.id}", fontSize = 26.sp, fontWeight = FontWeight.Bold )
                Row(Modifier.fillMaxWidth(),verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(selected.name, fontSize = 35.sp,textAlign = TextAlign.Start,fontWeight = FontWeight.Bold)
                    AsyncImage(   //imagen async porque se actualiza en la pantalla auto
                        model = selected.sprites.front_default,
                        contentDescription = selected.name,
                        Modifier.width(150.dp))
                }
                Column(Modifier.padding(25.dp).fillMaxWidth(), horizontalAlignment = Alignment.End)  //lo ultimo se ocupa para que este del lado derecho
                {
                    Text("Type: ${selected.types.joinToString(" /") { type -> type.type.name }}") //como son 1 o 2 valores los encadenamos, lazy column tambien funciona
                    Text("Height: ${selected.height}")
                    Text("Weight: ${selected.weight}")
                }

                LazyColumn() {      //listado de los stats que si queremos que se vean como lista
                    items(selected.stats) { stat ->
                        Text("${stat.stat.name}: ${stat.base_stat}")
                    }
                }


                Button(onClick = {  // agregar el pokemon a la lista de favoritos
                    if (!yaAgregado && selected!= null) {       //para verificar que no se dupliquen
                        vm.addfavs(it)
                        Log.d("Test","Agregaado a favoritos")}
                }){
                    Text(if (yaAgregado) "Agregado" else "Agregar a Favoritos")
                }




            }

        }
    }
}