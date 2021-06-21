package es.usj.section_1.adrian.peliculasapp

import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.aplicacionpeliculas.Actors
import com.example.aplicacionpeliculas.Genres
import com.example.aplicacionpeliculas.Movies
import com.example.aplicacionpeliculas._Config
import es.usj.section_1.adrian.peliculasapp.databinding.SplashFragmentBinding
import java.net.HttpURLConnection
import java.net.URL

class SplashFragment : Fragment() {

    private lateinit var viewModel: SplashViewModel
    private lateinit var binding: SplashFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.splash_fragment, container, false)
        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)
        binding.viewmodel=viewModel


        viewModel.navigateToMainScreenFragment.observe(viewLifecycleOwner, Observer {
            if (it == true){
                this.findNavController().navigate(SplashFragmentDirections
                    .actionSplashFragmentToMainScreenFragment())

                viewModel.onNavigatedToMainScreen()
            }
        })

        val backgroundThread = object : Thread() {
            override fun run() {

                val apiMoviesPref: SharedPreferences = activity!!.getPreferences( 0)
                val apiActorsPref: SharedPreferences = activity!!.getPreferences( 0)
                val apiGenresPref: SharedPreferences = activity!!.getPreferences( 0)
                var apiMovies = "[]"
                var apiActors = "[]"
                var apiGenres = "[]"

                var SERVER_CONNECTIVITY = true

                try {
                    HttpURLConnection.setFollowRedirects(false)
                    val con: HttpURLConnection =
                        URL(_Config.ENDPOINT).openConnection() as HttpURLConnection
                    con.setRequestMethod("HEAD")
                    con.setConnectTimeout(600)
                    con.getResponseCode() === HttpURLConnection.HTTP_OK
                } catch (e: java.lang.Exception) {
                    Log.e("ERROR", "no internet")
                    SERVER_CONNECTIVITY = false
                }

                if (SERVER_CONNECTIVITY) {
                    try {
                        //I convert into json

                        apiMovies =
                            getJsonFromURL(_Config.ENDPOINT + "/mobile/user/getMovies.php?user=" + _Config.USER + "&pass=" + _Config.PASSWORD + "&complex=true")
                        apiActors =
                            getJsonFromURL(_Config.ENDPOINT + "/mobile/user/getActors.php?user=" + _Config.USER + "&pass=" + _Config.PASSWORD + "&complex=true")
                        apiGenres =
                            getJsonFromURL(_Config.ENDPOINT + "/mobile/user/getGenres.php?user=" + _Config.USER + "&pass=" + _Config.PASSWORD + "&complex=true")

                        val apiMoviesPrefEditor = apiMoviesPref.edit()
                        apiMoviesPrefEditor.putString("apiMoviesPref", apiMovies)
                        apiMoviesPrefEditor.apply()

                        val apiActorsPrefEditor = apiActorsPref.edit()
                        apiActorsPrefEditor.putString("apiActorsPref", apiActors)
                        apiActorsPrefEditor.apply()

                        val apiGenresPrefEditor = apiGenresPref.edit()
                        apiGenresPrefEditor.putString("apiGenresPref", apiGenres)
                        apiGenresPrefEditor.apply()

                    } catch (e: Exception) {
                        e.printStackTrace();
                    }
                }else{
                    apiMovies =
                        apiMoviesPref.getString("apiMoviesPref", "[]").toString()
                    apiActors =
                        apiActorsPref.getString("apiActorsPref", "[]").toString()
                    apiGenres =
                        apiActorsPref.getString("apiActorsPref", "[]").toString()

                }

                Movies.parse(apiMovies)
                Actors.parse(apiActors)
                Genres.parse(apiGenres)






            }
        }


        backgroundThread.start()

        return binding.root

    }

    fun getJsonFromURL(wantedURL: String): String {
        return URL(wantedURL).readText()
    }


    }



