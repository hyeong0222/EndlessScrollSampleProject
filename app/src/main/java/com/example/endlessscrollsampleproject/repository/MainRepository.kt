package com.example.endlessscrollsampleproject.repository

import com.example.endlessscrollsampleproject.model.SearchUser
import com.example.endlessscrollsampleproject.network.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getSearchUserResult(searchQuery: String?, pageNumber: Int): SearchUser =
        apiService.getSearchUserResult(searchQuery, pageNumber)
}