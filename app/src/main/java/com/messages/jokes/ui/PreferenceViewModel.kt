package com.messages.jokes.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.messages.jokes.model.Result
import com.messages.jokes.utils.DataPreference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreferenceViewModel @Inject constructor(
    var preference: DataPreference
) : ViewModel() {
    private val _times = MutableLiveData<Result<Int>>()
    val times: LiveData<Result<Int>>
        get() = _times

    public fun setStoredTime(times: Int){
        preference.setStoredTag(times)
    }

    public fun getStoredTime(){
        viewModelScope.launch {
            _times.value = Result.success(preference.getStoredTag())
        }
    }
}