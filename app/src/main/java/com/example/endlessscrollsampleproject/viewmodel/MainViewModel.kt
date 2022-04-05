package com.example.endlessscrollsampleproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.endlessscrollsampleproject.model.User
import com.example.endlessscrollsampleproject.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository): ViewModel() {

    private val _userList = MutableLiveData<List<User?>>()
    val userList: LiveData<List<User?>> = _userList

    fun getSearchUserResult(searchQuery: String?, pageNumber: Int) {
        viewModelScope.launch {
            val result = mainRepository.getSearchUserResult(searchQuery, pageNumber)
            result.users?.let {
                _userList.value = _userList.value?.plus(it) ?: it
            }
        }
    }
}