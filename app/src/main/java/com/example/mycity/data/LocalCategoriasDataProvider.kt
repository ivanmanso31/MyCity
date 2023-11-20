package com.example.mycity.data

import com.example.mycity.R
import com.example.mycity.model.Categorias

object LocalCategoriasDataProvider {
    val defaultCategoria = getCategoriasData()[0]

    fun getCategoriasData(): List<Categorias> {
        return listOf(
            Categorias(
                id = 1,
                nombre = R.string.hoteles,
                imagen = R.drawable.hoteles,
            ),
            Categorias(
                id = 2,
                nombre = R.string.museos,
                imagen = R.drawable.museos
            ),
            Categorias(
                id = 3,
                nombre = R.string.restaurantes,
                imagen = R.drawable.restaurantes
            ),
            Categorias(
                id= 4,
                nombre = R.string.monumentos,
                imagen = R.drawable.monumentos
            ),
            Categorias(
                id = 5,
                nombre = R.string.ocio,
                imagen = R.drawable.ocio
            )
        )
    }
}