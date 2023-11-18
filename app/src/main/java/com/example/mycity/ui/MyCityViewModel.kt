package com.example.mycity.ui

import androidx.lifecycle.ViewModel
import com.example.mycity.data.LocalCategoriasDataProvider
import com.example.mycity.data.LocalRecomendacionesDataProvider
import com.example.mycity.model.Categorias
import com.example.mycity.model.Recomendaciones
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MyCityViewModel : ViewModel(){

    private val _uiStateCategorias = MutableStateFlow(
        CategoriasUiState(
            categoriasList = LocalCategoriasDataProvider.getCategoriasData(),
            currentCategorias = LocalCategoriasDataProvider.getCategoriasData().getOrElse(0){
                LocalCategoriasDataProvider.defaultCategoria
            }
        )
    )
    val uiStateCategorias: StateFlow<CategoriasUiState> = _uiStateCategorias

    private val _uiStateRecomendaciones = MutableStateFlow(
        RecomendacionesUiState(
            recomendacionesList = LocalRecomendacionesDataProvider.getRecomendacionesData(uiStateCategorias.value.currentCategorias.nombre),
            currentRecomendaciones = LocalRecomendacionesDataProvider.getRecomendacionesData(uiStateCategorias.value.currentCategorias.nombre).getOrElse(0){
                LocalRecomendacionesDataProvider.defaultRecomendaciones
            }
        )
    )

    val uiStateRecomendaciones: StateFlow<RecomendacionesUiState> = _uiStateRecomendaciones



    fun updateCurrentCategoria(selectedCategoria: Categorias) {
        _uiStateCategorias.update {
            it.copy(currentCategorias = selectedCategoria)
        }
    }

    fun updateCurrentRecomendacion(selectedRecomendacion: Recomendaciones) {
        _uiStateRecomendaciones.update {
            it.copy(currentRecomendaciones = selectedRecomendacion)
        }
    }

    fun updateRecomendacionesList(catego: Int){
        _uiStateRecomendaciones.update {
            it.copy(recomendacionesList = LocalRecomendacionesDataProvider.getRecomendacionesData(catego))
        }
    }
}

data class CategoriasUiState(
    val categoriasList: List<Categorias> = emptyList(),
    val currentCategorias: Categorias = LocalCategoriasDataProvider.defaultCategoria,
)

data class RecomendacionesUiState(
    val recomendacionesList: List<Recomendaciones> = emptyList(),
    val currentRecomendaciones: Recomendaciones = LocalRecomendacionesDataProvider.defaultRecomendaciones,
)