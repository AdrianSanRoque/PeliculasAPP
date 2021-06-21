package es.usj.section_1.adrian.peliculasapp

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bands.hambands.jsonparser.JsonParser
import com.example.aplicacionpeliculas.Actors
import com.example.aplicacionpeliculas.Genres
import com.example.aplicacionpeliculas.Movies
import com.example.aplicacionpeliculas._Config
import es.usj.section_1.adrian.peliculasapp.databinding.AddMovieFragmentBinding
import kotlinx.android.synthetic.main.add_movie_fragment.*

class AddMovieFragment : Fragment() {


    private lateinit var viewModel: AddMovieViewModel
    private lateinit var binding: AddMovieFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.add_movie_fragment, container, false)
        viewModel = ViewModelProvider(this).get(AddMovieViewModel::class.java)
        binding.viewmodel = viewModel

        binding.etId.setText(Movies.getNewId().toString())




        val adapterActors = ArrayAdapter(
            requireActivity(),
            android.R.layout.simple_spinner_item, Actors.toNamedArray()
        )
        spinnerActors.adapter = adapterActors

        val adapterGenres = ArrayAdapter(
            requireActivity(),
            android.R.layout.simple_spinner_item, Genres.toNamedArray()
        )
        spinnerGenres.adapter = adapterGenres



        btnAdd.setOnClickListener {
            var json = """{
            "id": """" + etId.text + """",
            "title": """" + etTitle.text + """",
            "description": """" + etDescription.text + """",
            "director": """" + etDirector.text + """",
            "year": """" + etYear.text + """",
            "runtime": """" + etRuntime.text + """",
            "rating": """" + etRating.text + """",
            "votes": """" + etVotes.text + """",
            "revenue": """" + etRevenue.text + """",
            "genres": [
            """" + (spinnerGenres.selectedItemPosition + 1) + """"
            ],
            "actors": [
            """" + (spinnerActors.selectedItemPosition + 1) + """"
            ]
            }"""

            val backgroundThread = object : Thread() {
                override fun run() {
                    try {
                        //Pusheo las peliculas
                        Movies.post(
                            _Config.ENDPOINT + "/mobile/user/addMovie.php?user=" + _Config.USER + "&pass=" + _Config.PASSWORD,
                            json
                        )
                    } catch (e: Exception) {
                        e.printStackTrace();
                    }
                }
            }
            backgroundThread.start()

            //Test
            /*val jsonParser = JsonParser(context)
            insertList(jsonParser.readAll())*/

            //Navegar desde add movie a splash activity para cargar la pelicula añadida

            viewModel.navigateToSplashFragment.observe(viewLifecycleOwner, Observer {
                if (it == true) {
                    this.findNavController().navigate(
                        AddMovieFragmentDirections
                            .actionAddMovieFragmentToSplashFragment()
                    )

                    viewModel.onNavigatedToSplashScreen()
                }
            })


        }
        return binding.root
    }
}







