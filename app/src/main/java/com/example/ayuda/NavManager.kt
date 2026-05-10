package com.example.ayuda

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


@Preview(showBackground = true)
@Composable
fun NavManager() {
    val navController = rememberNavController()
    val viewModel: PokenModel= viewModel()
    NavHost(navController, startDestination = "Habitat") {
        composable("Habitat") {  //son tipo ruta los composable toncesse ponen igual a como establecimos los objetos
            HabitatView(navController, viewModel )
        }
        composable("pokemon"){
            PokemonView(navController,viewModel)
        }
        composable("detail") {
            DetailScreen(navController,viewModel)
        }

        }
    }

