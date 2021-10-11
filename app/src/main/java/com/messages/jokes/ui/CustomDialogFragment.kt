package com.messages.jokes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.messages.jokes.databinding.AlertdialogLayoutBinding

class CustomDialogFragment : DialogFragment() {

    lateinit var binding: AlertdialogLayoutBinding
    val viewModel: JokeViewModel by activityViewModels() //shared viewModel Between @CustomDialogFragment and @MainActivity
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = AlertdialogLayoutBinding.inflate(inflater, container, false)
        this.dialog?.setTitle("Joke Details")
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.jokeWithDetails.observe(viewLifecycleOwner, Observer {
            binding.idTxt.text = it.id.toString()
            binding.typeTxt.text = it.type
            binding.setupTxt.text = it.setup
            binding.delTxt.text = it.delivery
        })
    }
}