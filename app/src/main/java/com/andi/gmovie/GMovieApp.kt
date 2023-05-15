package com.andi.gmovie


import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.andi.gmovie.navigate.Screen
import com.andi.gmovie.ui.screen.about.AboutScreen
import com.andi.gmovie.ui.screen.components.BottomBar
import com.andi.gmovie.ui.screen.detail.DetailScreen
import com.andi.gmovie.ui.screen.favorite.FavoriteScreen
import com.andi.gmovie.ui.screen.home.HomeScreen
import com.andi.gmovie.ui.screen.search.SearchScreen
import com.andi.gmovie.ui.theme.GMovieTheme
import com.andi.gmovie.utils.AppBarState


@Composable
fun GMovieApp(
    modifier: Modifier = Modifier,
    navController:NavHostController= rememberNavController(),
) {
    var appBarState by remember{
        (mutableStateOf(AppBarState()))
    }

    Scaffold(
        topBar =  {
                  TopAppBar(
                      title = { Text(text = appBarState.title) }
                  )
        },
        bottomBar = {
            BottomBar(navController)
        },
        modifier = modifier
    ) {innerPadding->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(route = Screen.Home.route,){
                HomeScreen(
                    navigateToDetail = {movieId->
                        navController.navigate(Screen.Detail.createRoute(movieId))
                    },
                    onComposing = {
                        appBarState=it
                    }
                )
            }
            composable(route = Screen.Search.route,){
                SearchScreen(
                    navigateToDetail = {movieId->
                        navController.navigate(Screen.Detail.createRoute(movieId))
                    },
                    onComposing = {
                        appBarState=it
                    }
                )
            }

            composable(Screen.Favorite.route){
                FavoriteScreen( navigateToDetail = {movieId->
                    navController.navigate(Screen.Detail.createRoute(movieId))
                },
                    onComposing = {
                        appBarState=it
                    }
                )
            }

            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument("movieId") { type = NavType.IntType }),
            ){ it ->
                val id = it.arguments?.getInt("movieId")?:1
                DetailScreen(
                    movieId = id,
                    onComposing = {
                        appBarState=it
                    }

                )
            }

            composable(Screen.About.route){
                AboutScreen(
                    onComposing = {
                        appBarState=it
                    }
                )
            }

        }
    }
}




@Preview(showBackground = true)
@Composable
fun GMoviePreview(){
    GMovieTheme {
        GMovieApp()
    }
}

