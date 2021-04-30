package com.example.devbytes.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.devbytes.database.getDatabase
import com.example.devbytes.domain.Video
import com.example.devbytes.network.Network
import com.example.devbytes.network.asDomainModel
import com.example.devbytes.repository.VideosRepository
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.IllegalArgumentException

class DevByteViewModel(application: Application): AndroidViewModel(application) {

   private val database = getDatabase(application)

    private val videosRepository = VideosRepository(database)

    init {
        viewModelScope.launch {
            videosRepository.refreshVideos()
        }
    }

    val playlist = videosRepository.videos


    class Factory(val app: Application):ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(DevByteViewModel::class.java)){
                return DevByteViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct ViewModel")
        }
    }

}