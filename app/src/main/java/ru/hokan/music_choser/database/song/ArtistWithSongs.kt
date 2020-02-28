package ru.hokan.music_choser.database.song

import androidx.room.Embedded
import androidx.room.Relation
import ru.hokan.music_choser.database.artist.Artist

data class ArtistWithSongs(
    @Embedded
    val artist: Artist,

    @Relation(parentColumn = "artistId", entityColumn = "songArtistId")
    val songList: List<Song>
)