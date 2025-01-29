package com.example.photolist.network

import retrofit2.http.GET

interface PhotosApiService {
    @GET("list")
    suspend fun getPhotos(): List<Photo>
}