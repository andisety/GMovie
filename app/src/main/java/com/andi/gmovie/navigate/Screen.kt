package com.andi.gmovie.navigate

sealed class Screen(val route:String){
    object Home:Screen("home")
    object Detail:Screen("home/{movieId}"){
        fun createRoute(movieId:Int)="home/$movieId"
    }
    object Favorite:Screen("favorite")
    object About:Screen("about")
    object Search:Screen("search")
}
