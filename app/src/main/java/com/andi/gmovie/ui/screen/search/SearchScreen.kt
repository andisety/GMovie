package com.andi.gmovie.ui.screen.search

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.andi.gmovie.data.MovieViewModel
import com.andi.gmovie.data.ViewModelFactory
import com.andi.gmovie.di.InjectionOnline
import com.andi.gmovie.utils.AppBarState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.andi.gmovie.ui.screen.components.SearchBar

@Composable
fun SearchScreen(
    navigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MovieViewModel = viewModel(
        factory = ViewModelFactory(
            InjectionOnline.provideRepository()
        )
    ),
    onComposing: (AppBarState) -> Unit,
){
    LaunchedEffect(key1 = true ){
        onComposing(AppBarState("Search"))
    }
    val query by viewModel.query
    Column(modifier = modifier) {
        SearchBar(modifier = modifier, query = query, onQueryChange = viewModel::search)

    }
}