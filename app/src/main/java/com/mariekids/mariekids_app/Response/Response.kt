package com.mariekids.mariekids_app.Response

import com.google.gson.annotations.SerializedName

data class Response(
    val age: String,
    val birthday: String,
    val birthyear: String,
    val email: String,
    val gender: String,
    val id: String,
    val mobile: String,
    val name: String,
    val nickname: String,
    @SerializedName("profile_image")
    val profileImage: String
)