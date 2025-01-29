package com.example.photolist.data

import com.example.photolist.network.PhotosApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val photosRepository: PhotosRepository
}


class DefaultAppContainer : AppContainer{

    private val baseURL = "https://picsum.photos/v2/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseURL)
        .build()

    private val retrofitService: PhotosApiService by lazy {
        retrofit.create(PhotosApiService::class.java)
    }

    override val photosRepository : PhotosRepository by lazy {
        NetworkPhotosRepository(retrofitService)
    }

}
