package es.usj.section_1.adrian.peliculasapp

import android.telecom.Call
import com.example.aplicacionpeliculas.Movie
import com.example.aplicacionpeliculas.Movies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface ApiService {
    @GET("C:/Users/adr_a/Downloads/Api/mobile/user/getMovies.php")
    fun  getAllMovies(): Call<List<"">>//Error en movie no se porque

    @GET("/{id}")
    fun getAllMoviesId(@Path("id")id:Int):retrofit2.Call<Movie>


    @GET
    fun getAllMovies(@Url url: String):Response<> //Response name
}