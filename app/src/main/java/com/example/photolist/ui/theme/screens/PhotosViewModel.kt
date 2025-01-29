package com.example.photolist.ui.theme.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.photolist.data.PhotosRepository
import com.example.photolist.network.Photo
import com.example.photolist.ui.theme.PhotosApplication
import kotlinx.coroutines.launch
import okio.IOException


sealed interface PhotosUiState{
    data class Success(val photos: List<Photo>): PhotosUiState
    object Error : PhotosUiState
    object Loading : PhotosUiState
}

class PhotosViewModel(private val photosRepository: PhotosRepository) : ViewModel() {

    var photosUiState : PhotosUiState by mutableStateOf(PhotosUiState.Loading)

    init {
        getPhotos()
    }

    private fun getPhotos(){
        viewModelScope.launch{
            photosUiState = try{
                PhotosUiState.Success(photosRepository.getPhotos())
            }catch (e:IOException){
                PhotosUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as PhotosApplication)
                val photosRepository = application.container.photosRepository
                PhotosViewModel(photosRepository = photosRepository)
            }
        }
    }
}