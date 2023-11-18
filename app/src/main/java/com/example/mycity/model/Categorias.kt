package com.example.mycity.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Categorias(
    val id: Int,
    @StringRes val nombre: Int,
    @DrawableRes val imagen: Int,
)
