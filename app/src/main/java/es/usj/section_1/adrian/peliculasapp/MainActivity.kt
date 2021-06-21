package es.usj.section_1.adrian.peliculasapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.aplicacionpeliculas.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
   /* val retrofit= Retrofit.Builder()
        .baseUrl("")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val api=retrofit.create(ApiService::class.java)*/
/*
    val retrofit:Retrofit= Retrofit.Builder()
        .baseUrl("http://www.omdbapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        */


    //val service = retrofit.create<ApiService>(ApiService::class.java)

    var movie: Movie?= Movie()   //le paso los parametros de movie

    /*service.getAllMovies().enqueue(object: Callback<List<Movie>>){

        override fun onResponse(call: Call<List<Movie>>,response: Response<List<Movie>>){

        val movies=response?.body()
            Log.i(TAG_LOGS,)

        }
        override fun onFailure(call: Call<List<Movie>>,t: Throwable){

            t?.printStackTrace()

        }

    }
    */

    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("http://www.omdbapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    }




}