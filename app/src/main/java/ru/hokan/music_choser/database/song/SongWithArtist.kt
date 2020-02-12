package ru.hokan.music_choser.database.song

import androidx.room.Embedded
import androidx.room.Relation
import ru.hokan.music_choser.database.artist.Artist

data class SongWithArtist(
    @Embedded
    val song: Song,

    @Relation(parentColumn = "songId", entityColumn = "artistId")
    var artist: Artist
)