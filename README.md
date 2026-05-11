Api_pokemon
        https://pokeapi.co/docs/v2#pokemon-habitats

utilizando la api de pokeapi, este es una prueba de aplicacion que muestre los pokemones que viven en los diversos habitats

se encuentra con comentarios y pasos asignados para el entendimiento y futuro desarollo


Actualmente incluye:
        busqueda por nombre
        listado de habitats
        listado de pokemones por habitat
        seleccion aleatoria de habitat
        listado de detalles de pokemon individual
        almacenamiento de nombre de entrenador
        almacenamiento de seleccion de pokemons favoritos
        listado de pokemons favoritos


requisitos no anotados son modificar los archivos de build gradel.kts(module:app), androidmanifest.xml y libs.version.toml

build gradel.kts(module:app)\n

en plugins agregar

    alias(libs.plugins.kotlin.serialization)\n
en dependencies agregar\n

     implementation("com.squareup.retrofit2:retrofit:2.9.0")
      implementation("com.squareup.retrofit2:converter-gson:2.9.0")
      implementation("io.coil-kt.coil3:coil-compose:3.4.0")
      implementation("io.coil-kt.coil3:coil-network-okhttp:3.4.0")
    

androidmanifest.xml

     <uses-permission android:name="android.permission.INTERNET"/>

libs.version.toml\n agregar en la seccion de plugins

    kotlin-serialization = { id = "org.jetbrains.kotlin.plugin.serialization", version.ref = "kotlin" }
