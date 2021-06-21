package es.usj.section_1.adrian.peliculasapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SplashViewModel : ViewModel() {

    //to navigate to main screen from splash screen
    private val _navigateToMainScreenFragment = MutableLiveData<Boolean?>()
    val navigateToMainScreenFragment : LiveData<Boolean?>
        get() = _navigateToMainScreenFragment

    fun navigateToMainScreen(){
        _navigateToMainScreenFragment.value=true
    }

    fun onNavigatedToMainScreen() {
        _navigateToMainScreenFragment.value = false
    }
}