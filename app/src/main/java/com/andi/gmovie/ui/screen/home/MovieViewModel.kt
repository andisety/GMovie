package com.andi.gmovie.data

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.andi.gmovie.model.Response
import com.andi.gmovie.model.UiState
import com.andi.gmovie.model.response.ResponseDetail
import com.andi.gmovie.model.response.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class MovieViewModel(private val repository: MovieRepository): ViewModel() {
    private val _movieDetail:MutableStateFlow<UiState<ResponseDetail>> = MutableStateFlow(UiState.Loading)
    private val _movieList:MutableStateFlow<UiState<List<Result>>> = MutableStateFlow(UiState.Loading)
    private val _movieListPopular:MutableStateFlow<UiState<List<Result>>> = MutableStateFlow(UiState.Loading)
    private val _movieListNowPlaying:MutableStateFlow<UiState<List<Result>>> = MutableStateFlow(UiState.Loading)
    private val _movieListTopRated:MutableStateFlow<UiState<List<Result>>> = MutableStateFlow(UiState.Loading)
    private val search : ArrayList<Result> = ArrayList()

    fun getAllMoviesUpcoming(){
        viewModelScope.launch(Dispatchers.IO) {
            when(val movie = repository.getMovieListUpcoming()) {
                is Response.Success -> {
                    _movieList.value = UiState.Success(movie.data)
                    search.addAll(movie.data)
                }
                is Response.Error -> _movieList.value = UiState.Error(movie.errorMessage)
            }
        }
    }
    fun getAllMoviesPopular(){
        viewModelScope.launch(Dispatchers.IO) {
            when(val movie = repository.getMovieListPopular()) {
                is Response.Success -> {
                    _movieListPopular.value = UiState.Success(movie.data)
                    search.addAll(movie.data)
                }
                is Response.Error -> _movieListPopular.value = UiState.Error(movie.errorMessage)
            }
        }
    }
    fun getAllMoviesNowPlaying(){
        viewModelScope.launch(Dispatchers.IO) {
            when(val movie = repository.getMovieListNowPlaying()) {
                is Response.Success -> {
                    _movieListNowPlaying.value = UiState.Success(movie.data)
                    search.addAll(movie.data)
                }
                is Response.Error -> _movieListNowPlaying.value = UiState.Error(movie.errorMessage)
            }
        }
    }
    fun getAllMoviesTopRated(){
        viewModelScope.launch(Dispatchers.IO) {
            when(val movie = repository.getMovieListTopRated()) {
                is Response.Success -> {
                    _movieListTopRated.value = UiState.Success(movie.data)
                    search.addAll(movie.data)
                }
                is Response.Error -> _movieListTopRated.value = UiState.Error(movie.errorMessage)
            }
        }
    }

    val movieListUpcoming : StateFlow<UiState<List<Result>>> get() = _movieList
    val movieListPopular : StateFlow<UiState<List<Result>>> get() = _movieListPopular
    val movieListNowPlaying : StateFlow<UiState<List<Result>>> get() = _movieListNowPlaying
    val movieListTopRated : StateFlow<UiState<List<Result>>> get() = _movieListTopRated

    fun getData(id:Int){
        viewModelScope.launch {
            when(val movie = repository.getDetail(id)) {
                is Response.Success -> _movieDetail.value = UiState.Success(movie.data)
                is Response.Error -> _movieDetail.value = UiState.Error(movie.errorMessage)
            }
        }
    }


    val movieDetail : StateFlow<UiState<ResponseDetail>> get() = _movieDetail


    //get search
    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

     fun search(newQuery: String) {
        _query.value = newQuery
         viewModelScope.launch {
             val searchTemp = search.filter {

                 it.title.contains(_query.value,true)
             }
             _movieList.value = UiState.Success(searchTemp)
             _movieListPopular.value = UiState.Success(searchTemp)
             _movieListNowPlaying.value = UiState.Success(searchTemp)
             _movieListTopRated.value = UiState.Success(searchTemp)
         }


    }
}

class ViewModelFactory(private val repository: MovieRepository): ViewModelProvider.NewInstanceFactory(){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}