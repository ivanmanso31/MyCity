package com.example.mycity.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mycity.R

enum class MyScreens(@StringRes val title: Int){
    Categories(title = R.string.categories),
    Recommendations(title = R.string.recommendations),
    Detail(title = R.string.detail)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCityApp(){
    val viewModel: MyCityViewModel = viewModel()
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = MyScreens.valueOf(
        backStackEntry?.destination?.route ?: MyScreens.Categories.name
    )


    Scaffold(
        topBar = {
            MyCityAppBar(
                currentScreenTitle = currentScreen.title,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = MyScreens.Categories.name
        ){
            composable(route = MyScreens.Categories.name){
                CategoriesScreen()
            }

            composable(route = MyScreens.Recommendations.name){
                RecommendationsScreen()
            }

            composable(route = MyScreens.Detail.name){
                DetailsScreen()
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