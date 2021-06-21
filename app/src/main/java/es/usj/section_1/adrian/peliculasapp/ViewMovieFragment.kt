package es.usj.section_1.adrian.peliculasapp

import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.aplicacionpeliculas.Movies
import com.google.gson.GsonBuilder
import es.usj.section_1.adrian.peliculasapp.databinding.ViewMovieFragmentBinding
import kotlinx.android.synthetic.main.splash_fragment.*
import java.io.InputStream
import java.net.URL
import android.content.Intent

class ViewMovieFragment : Fragment() {



    private lateinit var viewModel: ViewMovieViewModel
    private lateinit var binding: ViewMovieFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.view_movie_fragment, container, false)
        viewModel = ViewModelProvider(this).get(ViewMovieViewModel::class.java)
        binding.viewmodel = viewModel



        val moviePosition = Intent.getStringExtra("moviePosition");

        var currentMovie = Movies.array[moviePosition.toInt()]


        val backgroundThread = object : Thread() {
            override fun run() {
                try {

                    val movieExternalInfo =
                        getJsonFromURL("http://www.omdbapi.com/?t="+currentMovie.title+"&apikey=65d4a083")


                    val builder = GsonBuilder()
                    val o: Any = builder.create().fromJson(movieExternalInfo, Any::class.java)
                    val oMap = (o as Map<*, *>)

                    val url =
                        URL(oMap.get("Poster") as String?)
                    val bmp =
                        BitmapFactory.decodeStream(url.openConnection().getInputStream())


                    /*runOnUiThread(Runnable {
                        imageView.setImageBitmap(bmp)
                        binding.tvWriter.append(" " + oMap.get("Writer") as String?);
                    })
                     */

                } catch (e: Exception) {
                    e.printStackTrace();
                }
            }
        }

        backgroundThread.start()

        binding.tvId.append(" " + currentMovie.id.toString());
        binding.tvTitle.append(" " + currentMovie.title);
        binding.tvDescription.append(" " + currentMovie.description);
        binding.tvDirector.append(" " + currentMovie.director);
        binding.tvYear.append(" " + currentMovie.year.toString());
        binding.tvRuntime.append(" " + currentMovie.runtime.toString() + " minutes");
        binding.tvRating.append(" " + currentMovie.rating.toString() + " out of 10");
        binding.tvVotes.append(" " + currentMovie.votes.toString());
        binding.tvRevenue.append(" " + currentMovie.revenue.toString() + " millions of US$");


        viewModel.navigateToEditMovieFragment.observe(viewLifecycleOwner, Observer {
            if (it == true){
                this.findNavController().navigate(ViewMovieFragmentDirections.
                actionViewMovieFragmentToEditMovieFragment())

                    viewModel.onNavigatedToEditMovie()
            }
        })


        return binding.root
    }


    fun getJsonFromURL(wantedURL: String): String {
        return URL(wantedURL).readText()
    }

    fun LoadImageFromWebOperations(url: String?): Drawable? {
        return try {
            val `is`: InputStream = URL(url).content as InputStream
            Drawable.createFromStream(`is`, "src name")
        } catch (e: java.lang.Exception) {
            null
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ViewMovieViewModel::class.java)
        // TODO: Use the ViewModel
    }

}