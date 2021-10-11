package com.messages.jokes.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.messages.jokes.model.Joke
import com.messages.jokes.model.JokeResponse
import com.messages.jokes.network.JokeRepository
import com.messages.jokes.utils.NetworkHelper
import com.messages.jokes.model.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokeViewModel @Inject constructor(
    var jokeRepository: JokeRepository, val networkHelper: NetworkHelper
) : ViewModel() {
    private val _jokeList = MutableLiveData<Result<JokeResponse>>()
    val jokeList: LiveData<Result<JokeResponse>>
        get() = _jokeList

    private val _jokeWithDetails = MutableLiveData<Joke>()
    val jokeWithDetails: LiveData<Joke>
        get() = _jokeWithDetails

    public fun getJokeList(){
        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()) {
                jokeRepository.getJokes(10).let {
                    _jokeList.value = Result.loading(null)
                    if (it.isSuccessful) {
                        Log.e("getJokeList", "isSuccessful" )
                        _jokeList.value = Result.success(it.body())
                    } else {
                        Log.e("getJokeList", "error" )
                        _jokeList.value = Result.error(it.errorBody().toString())
                    }
                }
            } else _jokeList.postValue(Result.error("No internet connection"))
        }
    }
    fun selectJoke(joke: Joke){
        _jokeWithDetails.value = joke
    }
}