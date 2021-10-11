package com.messages.jokes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.messages.jokes.databinding.ActivityMainBinding
import com.messages.jokes.model.Joke
import com.messages.jokes.model.OnClickAction
import com.messages.jokes.model.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() , OnClickAction{
    lateinit var binding: ActivityMainBinding
    var customDialogFragment : CustomDialogFragment = CustomDialogFragment()
    val viewModel: JokeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getJokeList()
        viewModel.jokeList.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    Log.e("MainActivity", "SUCCESS " + it.data?.jokes?.size )
                    var adapter = JokeAdapter(it.data?.jokes!!)
                    binding.jokeRec.adapter = adapter
                }
                Status.LOADING -> {
                    Log.e("MainActivity", "LOADING" )
                }
                Status.ERROR -> {
                    Log.e("MainActivity", "ERROR")
                    Toast.makeText(this, "Something wrong happened!", Toast.LENGTH_SHORT).show()
                }
            }
            binding.swipeRef.isRefreshing = false
        })

        binding.swipeRef.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            viewModel.getJokeList()
        })
    }

    override fun showDialog(joke: Joke) {
        viewModel.selectJoke(joke = joke)
        customDialogFragment.show(supportFragmentManager, "CustomDialogFragment")
    }
}