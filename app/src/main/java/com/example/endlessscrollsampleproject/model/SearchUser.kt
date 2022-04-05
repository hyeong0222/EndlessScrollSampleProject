package com.example.endlessscrollsampleproject.model

import com.google.gson.annotations.SerializedName

data class SearchUser(
    @SerializedName("items")
    val users: List<User>? = null,
)
