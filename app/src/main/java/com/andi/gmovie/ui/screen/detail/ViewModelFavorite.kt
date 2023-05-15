package com.andi.gmovie.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andi.gmovie.data.local.MovieRepositoryLocal
import com.andi.gmovie.model.response.ResponseDetail
import kotlinx.coroutines.flow.*

class ViewModelFavorite  (private val movieRepositoryLocal: MovieRepositoryLocal) : ViewModel() {
    //cek ada didatabse tidak
    fun getFavMov(id:Int):Boolean{
        return movieRepositoryLocal.getMovie(id)
    }

    //get list movie
    fun getFavorite() = movieRepositoryLocal.getFavorite()


    //addDatatbse
     fun addFavorite(user: ResponseDetail) {
        movieRepositoryLocal.addFavorite(user, true)
    }

    //deleteData
    fun delete(id: Int) {
        movieRepositoryLocal.deleteMovie(id)
    }

}


    class ViewModelFactoryLokal(private val repository: MovieRepositoryLocal): ViewModelProvider.NewInstanceFactory(){
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ViewModelFavorite::class.java)) {
                return ViewModelFavorite(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
}