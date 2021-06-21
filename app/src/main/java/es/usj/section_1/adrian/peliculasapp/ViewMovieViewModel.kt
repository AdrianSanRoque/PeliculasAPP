package es.usj.section_1.adrian.peliculasapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewMovieViewModel : ViewModel() {

    //to navigate to edit movie fragment from main screen
    private val _navigateToEditMovieFragment = MutableLiveData<Boolean?>()
    val navigateToEditMovieFragment : LiveData<Boolean?>
        get() = _navigateToEditMovieFragment


    fun navigateToEditMovie(){
        _navigateToEditMovieFragment.value=true
    }

    fun onNavigatedToEditMovie() {
        _navigateToEditMovieFragment.value = false
    }
}