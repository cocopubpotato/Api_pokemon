package com.example.ayuda

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HabitatView(viewModel: PokenModel) {
    Column(
        Modifier
            //.fillMaxSize()
            .padding(15.dp)
    ) {

        Text("Bienvenido al mini pokedex")
        Text("Escoge\n")

        Button(onClick = {
            Log.d("TEST", "Botón presionado")
            viewModel.loadPokemon()
        }
        ) {
            Text("traer datos")
        }

        Text("Size: ${viewModel.pokemonlist.size}")
        Text("Habitat: ${viewModel.habitatname}")
        LazyColumn(Modifier.fillMaxWidth()) {
            items(viewModel.pokemonlist) { pokemon ->
                Text(pokemon.name)
            }
        }
    }
}