package com.example.mycity.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mycity.R
import com.example.mycity.utils.MyCityContenType

enum class MyScreens(@StringRes val title: Int){
    Categories(title = R.string.categories),
    Recommendations(title = R.string.recommendations),
    Detail(title = R.string.detail)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCityApp(
    windowSize: WindowWidthSizeClass,
){
    val viewModel: MyCityViewModel = viewModel()
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = MyScreens.valueOf(
        backStackEntry?.destination?.route ?: MyScreens.Categories.name
    )


    val contentType = when (windowSize){
        WindowWidthSizeClass.Compact,
        WindowWidthSizeClass.Medium -> MyCityContenType.ListOnly
        WindowWidthSizeClass.Expanded -> MyCityContenType.ListsAndDetail
            else -> MyCityContenType.ListOnly
    }


    Scaffold(
        topBar = {
            MyCityAppBar(
                currentScreenTitle = currentScreen.title,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->
        val uiStateCategorias by viewModel.uiStateCategorias.collectAsState()
        val uiStateRecomendaciones by viewModel.uiStateRecomendaciones.collectAsState()

        if(contentType == MyCityContenType.ListsAndDetail){
            MyCityListAndDetail(uiStateCategorias, uiStateRecomendaciones, viewModel, innerPadding, modifier = Modifier.fillMaxWidth())

        }
        else{
            NavHost(
                navController = navController,
                startDestination = MyScreens.Categories.name
            ) {
                composable(route = MyScreens.Categories.name) {
                    CategoriesScreen(
                        categorias = uiStateCategorias.categoriasList,
                        onClick = {
                            viewModel.updateCurrentCategoria(it)
                            viewModel.updateRecomendacionesList(it.nombre)
                            navController.navigate(MyScreens.Recommendations.name)
                        },
                        modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium)),
                        contentPadding = innerPadding
                    )
                }

                composable(route = MyScreens.Recommendations.name) {
                    RecommendationsScreen(
                        recomendaciones = uiStateRecomendaciones.recomendacionesList,
                        onClick = {
                            viewModel.updateCurrentRecomendacion(it)
                            navController.navigate(MyScreens.Detail.name)
                        },
                        modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium)),
                        contentPadding = innerPadding
                    )
                }

                composable(route = MyScreens.Detail.name) {
                    DetailsScreen(
                        selectedRecomendacion = uiStateRecomendaciones.currentRecomendaciones,
                        contentPadding = innerPadding,
                        onBackPressed = {
                            navController.navigate(MyScreens.Recommendations.name)
                        }
                    )
                }
            }
        }
    }




}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCityAppBar(
    @StringRes currentScreenTitle: Int,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(currentScreenTitle),
                fontWeight = FontWeight.Bold
                )
        },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back)
                    )
                }
            }
        },
        modifier = modifier
    )
}

@Composable
fun MyCityListAndDetail(
    uiStateCategorias: CategoriasUiState,
    uiStateRecomendaciones: RecomendacionesUiState,
    viewModel: MyCityViewModel,
    innerPadding: PaddingValues,
    modifier: Modifier

    ) {
    Row {
        Column (
            modifier = Modifier
                .fillMaxWidth(0.25f)
        ){
            CategoriesScreen(
                categorias = uiStateCategorias.categoriasList,
                onClick = {
                    viewModel.updateCurrentCategoria(it)
                    viewModel.updateRecomendacionesList(it.nombre)
                },
                modifier = modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium)),
                contentPadding = innerPadding
            )
        }

        Column (
            modifier = Modifier
                .fillMaxWidth(0.35f)
        ) {
            RecommendationsScreen(
                recomendaciones = uiStateRecomendaciones.recomendacionesList,
                onClick = {
                    viewModel.updateCurrentRecomendacion(it)
                },
                modifier = modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium)),
                contentPadding = innerPadding
            )
        }

        Column{
            DetailsScreen(
                selectedRecomendacion = uiStateRecomendaciones.currentRecomendaciones,
                contentPadding = innerPadding,
                onBackPressed = {
                }
            )
        }



    }
}