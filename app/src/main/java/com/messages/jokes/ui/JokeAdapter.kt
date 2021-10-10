package com.messages.jokes.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.messages.jokes.databinding.JokeItemLayoutBinding
import com.messages.jokes.model.Joke

class JokeAdapter(var jokeList: List<Joke>) : RecyclerView.Adapter<JokeAdapter.ViewHolder>() {

    lateinit var binding: JokeItemLayoutBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = JokeItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(joke = jokeList[position])

    }

    override fun getItemCount(): Int {
        return jokeList.size
    }

    inner class ViewHolder(var itemView: JokeItemLayoutBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        fun bindData(joke: Joke){
            binding.jokeTxt.text = joke.joke

        }
    }
}