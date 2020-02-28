package ru.hokan.music_choser.database.song

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "songs"
)
data class Song(
    @PrimaryKey(autoGenerate = true)
    val songId: Long?,
    val name: String,
    val songArtistId : Long
)