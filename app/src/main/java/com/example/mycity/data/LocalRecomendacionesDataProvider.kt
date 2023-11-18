package com.example.mycity.data

import androidx.compose.ui.res.stringResource
import androidx.core.content.res.TypedArrayUtils.getText
import com.example.mycity.R
import com.example.mycity.model.Recomendaciones

object LocalRecomendacionesDataProvider {
    val defaultRecomendaciones = getRecomendacionesData(R.string.hoteles)[0]

    fun getRecomendacionesData(tipo: Int): List<Recomendaciones> {

        val listaHoteles = listOf(
            Recomendaciones(
                id = 1,
                nombre = R.string.ritz,
                categoria = R.string.hoteles,
                detalles = R.string.detalles,
                imagen = R.drawable.ritz
            ),
            Recomendaciones(
                id = 2,
                nombre= R.string.detail,
                categoria = R.string.detail,
                detalles = R.string.detail,
                imagen = R.drawable.ic_launcher_foreground
            )
        )

        val listaMuseos = listOf<Recomendaciones>(
            Recomendaciones(
                id = 1,
                nombre = R.string.ritz,
                categoria = R.string.hoteles,
                detalles = R.string.detalles,
                imagen = R.drawable.ritz
            )
        )

        val listaRestaurantes = listOf<Recomendaciones>(

        )

        val listaMonumentos = listOf<Recomendaciones>(

        )

        val listaOcio = listOf<Recomendaciones>(

        )

        when(tipo){
            R.string.hoteles -> return listaHoteles
            R.string.museos -> return listaMuseos
            R.string.restaurantes -> return listaRestaurantes
            R.string.monumentos -> return listaMonumentos
            R.string.ocio -> return listaOcio

            else -> return listaMuseos
        }


    }
}