package com.example.photolist.data

import com.example.photolist.network.Photo
import com.example.photolist.network.PhotosApiService

interface PhotosRepository {
    suspend fun getPhotos(): List<Photo>
}


class NetworkPhotosRepository(private val photosApiService: PhotosApiService): PhotosRepository{
    override suspend fun getPhotos(): List<Photo> {
        return photosApiService.getPhotos()
    }
}
