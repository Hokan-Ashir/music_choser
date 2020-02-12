package ru.hokan.music_choser.database.song

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import ru.hokan.music_choser.database.artist.Artist

@Entity(
    tableName = "songs",
    foreignKeys = [ForeignKey(
        entity = Artist::class,
        childColumns = ["artistId"],
        parentColumns = ["songId"]
    )]
)
data class Song(
    @PrimaryKey(autoGenerate = true)
    val songId: Long?,
    val name: String?,
    val artistId: Long?
)