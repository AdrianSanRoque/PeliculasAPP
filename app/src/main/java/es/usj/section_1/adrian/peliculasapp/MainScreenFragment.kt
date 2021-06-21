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
import com.example.aplicacionpeliculas.Movies
import es.usj.section_1.adrian.peliculasapp.databinding.MainScreenFragmentBinding
import kotlinx.android.synthetic.main.main_screen_fragment.*

class MainScreenFragment : Fragment() {



    private lateinit var adapter: CustomMovieAdapter
    private lateinit var viewModel: MainScreenViewModel
    private lateinit var binding: MainScreenFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.main_screen_fragment, container, false)
        viewModel = ViewModelProvider(this).get(MainScreenViewModel::class.java)
        binding.viewmodel=viewModel



        adapter = CustomMovieAdapter(
            context = requireActivity(),
            resourceId = R.layout.row_element,
            items = Movies.list
        )

        lvMain.adapter = this.adapter


        viewModel.navigateToAddMovieFragment.observe(viewLifecycleOwner, Observer {
            if (it == true){
                this.findNavController().navigate(MainScreenFragmentDirections
                    .actionMainScreenFragmentToAddMovieFragment())

                    viewModel.onNavigatedToAddMovie()
            }
        })



    viewModel.navigateToEditMovieFragment.observe(viewLifecycleOwner, Observer {
        if (it == true){
            this.findNavController().navigate(MainScreenFragmentDirections
                .actionMainScreenFragmentToEditMovieFragment())

                viewModel.onNavigatedToEditMovie()
        }
    })


        viewModel.navigateToContactFragment.observe(viewLifecycleOwner, Observer {
            if (it == true){
                this.findNavController().navigate(MainScreenFragmentDirections
                    .actionMainScreenFragmentToContactFragment())

                    viewModel.onNavigatedToContact()
            }
        })



        viewModel.navigateToViewMovieFragment.observe(viewLifecycleOwner, Observer {
            if (it == true){
                this.findNavController().navigate(MainScreenFragmentDirections
                    .actionMainScreenFragmentToViewMovieFragment())

                    viewModel.onNavigatedToEditMovie()
            }
        })

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainScreenViewModel::class.java)
        // TODO: Use the ViewModel
    }

}