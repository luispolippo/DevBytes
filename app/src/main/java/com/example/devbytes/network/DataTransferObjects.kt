package com.example.devbytes.network


import com.example.devbytes.database.DatabaseVideo
import com.example.devbytes.domain.Video
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class NetworkVideoContainer(val videos: List<NetworkVideo>)

@JsonClass(generateAdapter = true)
data class NetworkVideo(
    val title: String,
    val description: String,
    val url: String,
    val update: String,
    val thumbnail: String,
    val closedCaption: String?
)

fun NetworkVideoContainer.asDomainModel(): List<Video> {
    return videos.map {
        Video(
            title = it.title,
            description = it.description,
            url = it.url,
            update = it.update,
            thumbnail = it.thumbnail
        )
    }
}

fun NetworkVideoContainer.asDatabaseModel(): Array<DatabaseVideo>{
    return videos.map {
        DatabaseVideo(
            title = it.title,
            description = it.description,
            url = it.url,
            update = it.update,
            thumbnail = it.thumbnail
        )
    }.toTypedArray()
}