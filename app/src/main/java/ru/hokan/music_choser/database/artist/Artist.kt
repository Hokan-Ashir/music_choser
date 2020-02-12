package ru.hokan.music_choser.database.artist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "artists", indices = [Index("name", unique = true)])
data class Artist(
    @PrimaryKey(autoGenerate = true)
    val artistId: Long?,
    val name: String
)