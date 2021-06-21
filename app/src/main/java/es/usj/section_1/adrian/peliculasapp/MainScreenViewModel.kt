package es.usj.section_1.adrian.peliculasapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainScreenViewModel : ViewModel() {

    //to navigate to add movie screen from main screen
    private val _navigateToAddMovieFragment = MutableLiveData<Boolean?>()
    val navigateToAddMovieFragment : LiveData<Boolean?>
        get() = _navigateToAddMovieFragment


    //to navigate to contact fragment from main screen
    private val _navigateToContactFragment = MutableLiveData<Boolean?>()
    val navigateToContactFragment : LiveData<Boolean?>
        get() = _navigateToContactFragment

    //to navigate to edit movie fragment from main screen
    private val _navigateToEditMovieFragment = MutableLiveData<Boolean?>()
    val navigateToEditMovieFragment : LiveData<Boolean?>
        get() = _navigateToEditMovieFragment

    //to navigate to view movie fragment from main screen
    private val _navigateToViewMovieFragment = MutableLiveData<Boolean?>()
    val navigateToViewMovieFragment : LiveData<Boolean?>
        get() = _navigateToViewMovieFragment



    fun navigateToAddMovie(){
        _navigateToAddMovieFragment.value = true
    }

    fun navigateToContact(){
        _navigateToContactFragment.value=true
    }

    fun navigateToEditMovie(){
        _navigateToEditMovieFragment.value=true
    }

    fun navigateToViewMovie(){
        _navigateToViewMovieFragment.value=true
    }

    fun onNavigatedToEditMovie() {
        _navigateToEditMovieFragment.value = false
    }

    fun onNavigatedToAddMovie() {
        _navigateToAddMovieFragment.value = false
    }

    fun onNavigatedToContact() {
        _navigateToContactFragment.value = false
    }

    fun onNavigatedViewMovie() {
        _navigateToViewMovieFragment.value = false
    }


}