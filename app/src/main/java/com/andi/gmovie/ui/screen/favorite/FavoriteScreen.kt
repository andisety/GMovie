package com.andi.gmovie.ui.screen.favorite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.andi.gmovie.data.local.entity.MovieEntity
import com.andi.gmovie.di.Injection
import com.andi.gmovie.ui.screen.components.MovieListItem
import com.andi.gmovie.ui.screen.detail.ViewModelFactoryLokal
import com.andi.gmovie.ui.screen.detail.ViewModelFavorite
import com.andi.gmovie.utils.AppBarState

@Composable
fun FavoriteScreen(
    modifier: Modifier=Modifier,
    navigateToDetail: (Int) -> Unit,
    viewModel: ViewModelFavorite = viewModel(factory = ViewModelFactoryLokal(Injection.provideRepository(
        LocalContext.current))),
   onComposing:(AppBarState)->Unit
){

    val movie = viewModel.getFavorite()
    val favorite = (movie.isEmpty())

    LaunchedEffect(key1 = true ){
        onComposing(AppBarState("Favorite"))
    }

    if (favorite){
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Data Kosong",
            )
        }
    }else{
        Favoritecontent(
            movie = movie,
            navigateToDetail = navigateToDetail
        )
    }
}


@Composable
fun Favoritecontent(
    movie: List<MovieEntity>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit,
){
        LazyColumn {
            items(movie,key={it.id}) { movie ->
                MovieListItem(
                    title = movie.title,
                    desc = movie.desc,
                    photoUrl ="http://image.tmdb.org/t/p/w500/"+movie.url,
                    modifier = modifier
                        .fillMaxWidth()
                        .clickable { navigateToDetail(movie.id) },
                )
            }
        }
}
