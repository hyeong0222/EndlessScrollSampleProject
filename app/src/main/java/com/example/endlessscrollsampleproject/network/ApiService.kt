package com.example.endlessscrollsampleproject.network

import com.example.endlessscrollsampleproject.model.SearchUser
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/search/users")
    suspend fun getSearchUserResult(
        @Query("q") searchQuery: String?,
        @Query("page") pageNumber: Int,
    ): SearchUser
}