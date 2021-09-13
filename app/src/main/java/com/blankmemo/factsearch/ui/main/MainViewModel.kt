package com.blankmemo.factsearch.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.blankmemo.factsearch.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor
    (
//    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

//  private val _response: MutableLiveData<NetworkResult<DogResponse>> = MutableLiveData()
//  val response: LiveData<NetworkResult<DogResponse>> = _response
//
//  fun fetchDogResponse() = viewModelScope.launch {
//    repository.getDog().collect { values ->
//      _response.value = values
//    }
//  }

}
