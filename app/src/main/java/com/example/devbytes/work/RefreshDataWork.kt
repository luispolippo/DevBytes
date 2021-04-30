package com.example.devbytes.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.devbytes.database.getDatabase
import com.example.devbytes.repository.VideosRepository
import retrofit2.HttpException

class RefreshDataWork(appContext: Context, params: WorkerParameters): CoroutineWorker(appContext, params){

    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = VideosRepository(database)

        return try {
            repository.refreshVideos()
            Result.success()
        } catch (e: HttpException){
            Result.retry()
        }
    }

    companion object{
        const val WORK_NAME = "RefreshDataWorker"
    }
}