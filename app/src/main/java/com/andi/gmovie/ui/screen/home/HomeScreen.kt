package com.andi.gmovie.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.andi.gmovie.data.MovieViewModel
import com.andi.gmovie.data.ViewModelFactory
import com.andi.gmovie.di.InjectionOnline
import com.andi.gmovie.model.UiState
import com.andi.gmovie.model.response.Result
import com.andi.gmovie.ui.screen.components.MovieListItemImage
import com.andi.gmovie.ui.screen.components.SearchBarFake
import com.andi.gmovie.utils.AppBarState

@Composable
fun HomeScreen(
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier=Modifier,
    viewModel: MovieViewModel = viewModel(factory = ViewModelFactory(InjectionOnline.provideRepository())),
    onComposing: (AppBarState) -> Unit,
    ){
    LaunchedEffect(key1 = true ){
        onComposing(AppBarState("GMovie App"))
    }

    Column(modifier = modifier
        .verticalScroll(rememberScrollState())
        .fillMaxSize()
        ) {
        SearchBarFake(modifier = modifier )
        //upcoming
        Text(
            modifier = modifier.padding(10.dp),
            text = "Up Coming",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
            )
        viewModel.movieListUpcoming.collectAsState(initial = UiState.Loading).value.let { movies->
            when(movies){
                is UiState.Loading->{
                    viewModel.getAllMoviesUpcoming()
                     CircularProgressIndicator(
                         modifier = Modifier
                             .align(Alignment.CenterHorizontally)
                             .size(50.dp),
                         color = Color.Blue,
                         strokeWidth = 10.dp)
                }
                is UiState.Error->{
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "No Internet",
                        )
                    }
                }
                is UiState.Success->{
                    val mov = movies.data.distinctBy { it.id }
                    HomeContent(
                        movie =mov ,
                        navigateToDetail = navigateToDetail,
                        modifier = modifier
                    )
                }
            }
        }
        //popular
        Text(
            modifier = modifier.padding(10.dp),
            text = "Popular",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        viewModel.movieListPopular.collectAsState(initial = UiState.Loading).value.let { movies->
            when(movies){
                is UiState.Loading->{
                    viewModel.getAllMoviesPopular()
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .size(50.dp),
                        color = Color.Blue,
                        strokeWidth = 10.dp)
                }
                is UiState.Error->{
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "No Internet",
                        )
                    }
                }
                is UiState.Success->{
                    val mov = movies.data.distinctBy { it.id }
                    HomeContent(
                        movie =mov ,
                        navigateToDetail = navigateToDetail,
                        modifier = modifier
                    )
                }
            }
        }
        Text(
            modifier = modifier.padding(10.dp),
            text = "Top Rated",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        viewModel.movieListTopRated.collectAsState(initial = UiState.Loading).value.let { movies->
            when(movies){
                is UiState.Loading->{
                    viewModel.getAllMoviesTopRated()
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .size(50.dp),
                        color = Color.Blue,
                        strokeWidth = 10.dp)
                }
                is UiState.Error->{
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "No Internet",
                        )
                    }
                }
                is UiState.Success->{
                    val mov = movies.data.distinctBy { it.id }
                    HomeContent(
                        movie =mov ,
                        navigateToDetail = navigateToDetail,
                        modifier = modifier
                    )
                }
            }
        }

        Text(
            modifier = modifier.padding(10.dp),
            text = "Now Playing",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        viewModel.movieListNowPlaying.collectAsState(initial = UiState.Loading).value.let { movies->
            when(movies){
                is UiState.Loading->{
                    viewModel.getAllMoviesNowPlaying()
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .size(50.dp),
                        color = Color.Blue,
                        strokeWidth = 10.dp)
                }
                is UiState.Error->{
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "No Internet",
                        )
                    }
                }
                is UiState.Success->{
                    val mov = movies.data.distinctBy { it.id }
                    HomeContent(
                        movie =mov ,
                        navigateToDetail = navigateToDetail,
                        modifier = modifier
                    )
                }
            }
        }

    }
}

@Composable
fun HomeContent(
    movie: List<Result>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit,
){
    LazyRow {
        items(items=movie,key={it.id}) { movie ->
            MovieListItemImage(
                modifier = modifier
                    .clickable { navigateToDetail(movie.id) },
                title = movie.title,
                photoUrl ="http://image.tmdb.org/t/p/w500/"+movie.posterPath,

            )
        }
    }
}
