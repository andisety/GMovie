package com.andi.gmovie.ui.screen.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.andi.gmovie.navigate.NavigationItem
import com.andi.gmovie.navigate.Screen


@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    BottomNavigation(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigatonItem = listOf(
            NavigationItem("Home", Icons.Default.Home, Screen.Home),
            NavigationItem("Search", Icons.Default.Search, Screen.Search),
            NavigationItem("Favorite", Icons.Default.Favorite, Screen.Favorite),
            NavigationItem("About", Icons.Default.Person, Screen.About),
        )
        BottomNavigation{
            navigatonItem.map {item->
                BottomNavigationItem(
                    icon = {
                        Icon(imageVector = item.icon,
                            contentDescription = item.title)
                    },
                    label = { Text(text = item.title) },
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigate(item.screen.route){
                            popUpTo(navController.graph.findStartDestination().id){
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true

                        }
                    }
                )
            }
        }
    }
}