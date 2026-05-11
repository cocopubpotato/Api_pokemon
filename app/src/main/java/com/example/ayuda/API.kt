package com.example.ayuda

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.collections.emptyList


//PASO 4

//aqui es donde se realizan las funciones para recuperar datos del api
class API {
    private val service = RetroFitHelper.getRetrofitService() //verificamos que se reciban los datos

    //si solo quisieras pasar los nombres de los habitats esto funciona
    suspend fun getHabitats(): List<HabitatItem> {
        val response = service.getHabitats()
        return if(response.isSuccessful) {
            response.body()?.results ?: emptyList()
        } else {
            emptyList()
        }
    }


    //pero como queria recibir el nombre del habitat pos recolectamos todos los datos
    suspend fun getHabitatFull(id: Int):datoHabitat?{  //ya que son los datos de un habitat especifico requerimos la id de este
        val response= service.getHabitat(id)
        return if (response.isSuccessful)response.body() else null
    }

    //para recolectar los datos de los pokemonsitos
    suspend fun getPokenDetail(name: String): PokemonDetails?{
        val  response= service.getPokenDetails(name)
        return if (response.isSuccessful){
            response.body()
        }else{
            null
        }
    }
}

//clase para la preferencia
class Favoritos(private val  contexto: Context){
    companion object{
        val Context.dataStore: DataStore<Preferences>
                by preferencesDataStore(name = "configuraciones")
        val Ident = intPreferencesKey(name = "id")
        val NAME = stringPreferencesKey(name = "name")
    }
    //modo lectura de la info
    val id: Flow<Int> = contexto.dataStore.data.map { preferences ->
        preferences[Ident] ?: 0
    }
    val name: Flow<String> = contexto.dataStore.data.map { preferences ->
        preferences[NAME] ?: "Sin nombre asignado"
    }

    //Guardar los datos
    suspend fun guardarDatosPokens(
        identify: Int, nombre: String
    ){
        contexto.dataStore.edit{settings->
            settings[Ident]= identify
            settings[NAME]= nombre
        }
    }
}