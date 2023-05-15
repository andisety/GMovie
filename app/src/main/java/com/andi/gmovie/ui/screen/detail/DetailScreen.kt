package com.andi.gmovie.ui.screen.detail

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.andi.gmovie.data.MovieViewModel
import com.andi.gmovie.data.ViewModelFactory
import com.andi.gmovie.di.Injection
import com.andi.gmovie.di.InjectionOnline
import com.andi.gmovie.model.UiState
import com.andi.gmovie.ui.screen.components.DetailMovie
import com.andi.gmovie.utils.AppBarState

@Composable
fun DetailScreen(
    movieId:Int,
    vieModel:MovieViewModel = viewModel(factory = ViewModelFactory(InjectionOnline.provideRepository())),
    vieModelLokal:ViewModelFavorite = viewModel(factory = ViewModelFactoryLokal(Injection.provideRepository(
        LocalContext.current))),
    onComposing:(AppBarState)->Unit,
    modifier: Modifier = Modifier,
){
    val context = LocalContext.current
    vieModel.movieDetail.collectAsState(initial = UiState.Loading).value.let { movie->

            when(movie){
                is UiState.Loading->{
                    CircleBar()
                    vieModel.getData(movieId)
                }
                is UiState.Error->{
                    Column(
                        modifier = modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "No Internet",
                        )
                    }
                }
                is UiState.Success->{
                    LaunchedEffect(key1 = true,  ){
                        onComposing(AppBarState(movie.data.title))
                    }
                    val isFavorite = vieModelLokal.getFavMov(movieId)
                    val genress:ArrayList<String> = ArrayList()
                    movie.data.genres.forEach {
                        genress.add(it.name)
                    }
                    val genre = genress.joinToString (",")
                    DetailMovie(
                        title = movie.data.title,
                        url = movie.data.posterPath,
                        genre =genre,
                        date =movie.data.releaseDate,
                        rate = (movie.data.voteAverage/2),
                        desc = movie.data.overview,
                        check=isFavorite,

                        ){
                        if (isFavorite) {
                            vieModelLokal.delete(movie.data.id)
                            Toast.makeText(context,"Delete",Toast.LENGTH_SHORT).show()
                        }
                        if(!isFavorite){
                            vieModelLokal.addFavorite(movie.data)
                            Toast.makeText(context,"Add",Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                else -> { Text(text =movie.toString())}
            }


    }
}

@Composable
private fun CircleBar(modifier: Modifier=Modifier){
    Box(modifier = Modifier.fillMaxWidth().fillMaxHeight()){
        CircularProgressIndicator(
            modifier = modifier
                .size(50.dp)
                .align(Alignment.Center),
            color = Color.Blue,
            strokeWidth = 10.dp)
    }
}