package com.example.photolist.ui.theme

import android.app.Application
import com.example.photolist.data.AppContainer
import com.example.photolist.data.DefaultAppContainer

class PhotosApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}