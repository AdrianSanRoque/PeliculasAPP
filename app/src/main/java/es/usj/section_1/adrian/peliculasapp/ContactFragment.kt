package es.usj.section_1.adrian.peliculasapp

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import es.usj.section_1.adrian.peliculasapp.databinding.ContactFragmentBinding
import kotlinx.android.synthetic.main.contact_fragment.*

class ContactFragment : Fragment() {


    private lateinit var viewModel: ContactViewModel
    private lateinit var binding:ContactFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.contact_fragment, container, false)
        viewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
        binding.viewmodel=viewModel


        //ASK FOR IT
        btnCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + etPhone.text));
            startActivity(intent);
        }

        btnSend.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:")
            intent.putExtra(Intent.EXTRA_EMAIL, etEmail.text)
            startActivity(intent)
        }

        btnOpen.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse(etWeb.text.toString())
            startActivity(openURL)
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ContactViewModel::class.java)
        // TODO: Use the ViewModel
    }

}