package com.mariekids.mariekids_app.Service

import com.mariekids.mariekids_app.Response.UserInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface LoginService {
    @GET("/nid/me")
    fun getLoginUser(
        @Header("X-Naver-Client-Id") cliendID: String,
        @Header("X-Naver-Client-Secret") clientPW: String,
        @Header("Authorization") token: String
    ) : Call<UserInfo>
}