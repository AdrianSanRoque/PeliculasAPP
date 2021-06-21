package es.usj.section_1.adrian.peliculasapp

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.example.aplicacionpeliculas.Actors
import com.example.aplicacionpeliculas.Genres
import com.example.aplicacionpeliculas.Movies
import com.example.aplicacionpeliculas.Movies.Companion.json
import com.example.aplicacionpeliculas._Config
import es.usj.section_1.adrian.peliculasapp.databinding.EditMovieFragmentBinding
import android.content.Intent


class EditMovieFragment : Fragment() {


    private lateinit var viewModel: EditMovieViewModel
    private lateinit var binding: EditMovieFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.edit_movie_fragment, container, false)
        viewModel = ViewModelProvider(this).get(EditMovieViewModel::class.java)
        binding.viewmodel = viewModel

        val moviePosition = Intent.getStringExtra("moviePosition")
        var currentMovie = Movies.array[moviePosition.toInt()]




        /*companion object {
        fun newInstance(position: Int): ViewStackListFragment {
            val fragment = ViewStackListFragment()
            val args = Bundle()
            args.putInt("position", position)
            fragment.setArguments(args)
            return fragment
        }
    }*/

        /*fun newInstance(index: Int): MyFragment {
    val f = MyFragment ()
    // Pass index input as an argument.
    val args = Bundle()
    args.putInt("index", index)
    f.setArguments(args)
    return f
}*/

        //Get string moveposition
        fun  newInstance(index:String): EditMovieFragment{
            val currentMovie= EditMovieFragment()//currentMovie moviePosition
            val moviePosition = Bundle()
            moviePosition.putString("moviePosition", currentMovie.toString())
            currentMovie.setArguments(moviePosition)
            return currentMovie

        }

            //val args = RegistrationStep2FragmentArgs.fromBundle(requireArguments())
        //val user = args.user

        val adapterActors = ArrayAdapter(
            requireActivity(),
            android.R.layout.simple_spinner_item, Actors.toNamedArray()
        )
        binding.spinnerActors.adapter = adapterActors
        binding.spinnerActors.setSelection(currentMovie.actors[0] - 1)

        val adapterGenres = ArrayAdapter(
            requireActivity(),
            android.R.layout.simple_spinner_item, Genres.toNamedArray()
        )
        binding.spinnerGenres.adapter = adapterGenres
        binding.spinnerGenres.setSelection(currentMovie.genres[0] - 1)



        binding.etId.setText(currentMovie.id.toString())
        binding.etTitle.setText(currentMovie.title)
        binding.etDescription.setText(currentMovie.description)
        binding.etDirector.setText(currentMovie.director)
        binding.etYear.setText(currentMovie.year.toString())
        binding.etRuntime.setText(currentMovie.runtime.toString())
        binding.etRating.setText(currentMovie.rating.toString())
        binding.etVotes.setText(currentMovie.votes.toString())
        binding.etRevenue.setText(currentMovie.revenue.toString())

        binding.btnEdit.setOnClickListener {

            var json = """{
            "id": """" + binding.etId.text + """",
            "title": """" + binding.etTitle.text + """",
            "description": """" + binding.etDescription.text + """",
            "director": """" + binding.etDirector.text + """",
            "year": """" + binding.etYear.text + """",
            "runtime": """" + binding.etRuntime.text + """",
            "rating": """" + binding.etRating.text + """",
            "votes": """" + binding.etVotes.text + """",
            "revenue": """" + binding.etRevenue.text + """",
            "genres": [
            """" + (binding.spinnerGenres.selectedItemPosition + 1) + """"
            ],
            "actors": [
            """" + (binding.spinnerActors.selectedItemPosition + 1) + """"
            ]
            }"""


            val backgroundThread = object : Thread() {
                override fun run() {
                    try {
                        Movies.post(
                            _Config.ENDPOINT + "/mobile/user/updateMovie.php?user=" + _Config.USER + "&pass=" + _Config.PASSWORD,
                            json
                        )
                    } catch (e: Exception) {
                        e.printStackTrace();
                    }
                }
            }
            backgroundThread.start()

        }

        return binding.root
    }
}





