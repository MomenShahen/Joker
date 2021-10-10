package com.messages.jokes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.messages.jokes.databinding.ActivityMainBinding
import com.messages.jokes.model.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val viewModel: JokeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getJokeList()
        viewModel.jokeList.observe(this, Observer {
            binding.swipeRef.isRefreshing = false
            when(it.status){
                Status.SUCCESS->{
                    var adapter = JokeAdapter(it.data?.jokes!!)
                    binding.jokeRec.adapter = adapter
                }
                Status.LOADING->{

                }
                Status.ERROR->{
                    Toast.makeText(this, "Something wrong happened!", Toast.LENGTH_SHORT).show()
                }
            }
        })

        binding.swipeRef.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            viewModel.getJokeList()
        })
    }
}