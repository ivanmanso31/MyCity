package com.example.mycity.model

import androidx.annotation.StringRes

data class Recomendaciones(
    val id: Int,
    @StringRes val nombre: Int,
    @StringRes val categoria: Int,
    @StringRes val detalles: Int
    )
