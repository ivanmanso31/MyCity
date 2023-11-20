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
                detalles = R.string.Lorem_ipsum,
                imagen = R.drawable.hoteles
            ),
            Recomendaciones(
                id = 2,
                nombre= R.string.palace,
                categoria = R.string.hoteles,
                detalles = R.string.Lorem_ipsum,
                imagen = R.drawable.hoteles
            )
        )

        val listaMuseos = listOf(
            Recomendaciones(
                id = 1,
                nombre = R.string.museo_del_prado,
                categoria = R.string.museos,
                detalles = R.string.Lorem_ipsum,
                imagen = R.drawable.museos
            ),
            Recomendaciones(
                id = 2,
                nombre = R.string.museo_sorolla,
                categoria = R.string.museos,
                detalles = R.string.Lorem_ipsum,
                imagen = R.drawable.museos
            )
        )

        val listaRestaurantes = listOf(
            Recomendaciones(
                id = 1,
                nombre = R.string.casa_pepe,
                categoria = R.string.restaurantes,
                detalles = R.string.Lorem_ipsum,
                imagen = R.drawable.restaurantes
            ),
            Recomendaciones(
                id = 2,
                nombre = R.string.el_rincon,
                categoria = R.string.restaurantes,
                detalles = R.string.Lorem_ipsum,
                imagen = R.drawable.restaurantes
            )
        )

        val listaMonumentos = listOf(
            Recomendaciones(
                id = 1,
                nombre = R.string.palacio_real,
                categoria = R.string.monumentos,
                detalles = R.string.Lorem_ipsum,
                imagen = R.drawable.monumentos
            ),
            Recomendaciones(
                id = 2,
                nombre = R.string.plaza_mayor,
                categoria = R.string.monumentos,
                detalles = R.string.Lorem_ipsum,
                imagen = R.drawable.monumentos
            )
        )

        val listaOcio = listOf(
            Recomendaciones(
                id = 1,
                nombre = R.string.cine_el_primillo,
                categoria = R.string.ocio,
                detalles = R.string.Lorem_ipsum,
                imagen = R.drawable.ocio
            ),
            Recomendaciones(
                id = 2,
                nombre = R.string.feria_las_golondrinas,
                categoria = R.string.ocio,
                detalles = R.string.Lorem_ipsum,
                imagen = R.drawable.ocio
            )
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