package es.usj.section_1.adrian.peliculasapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddMovieViewModel : ViewModel() {



    private val _navigateToSplashFragment = MutableLiveData<Boolean?>()
    val navigateToSplashFragment : LiveData<Boolean?>
        get() = _navigateToSplashFragment

    fun navigateToSplashScreen(){
        _navigateToSplashFragment.value = true
    }

    fun onNavigatedToSplashScreen() {
        _navigateToSplashFragment.value = false
    }
}