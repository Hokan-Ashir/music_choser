package ru.hokan.music_choser.database.artist

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import ru.hokan.music_choser.database.song.ArtistWithSongs

@Dao
interface ArtistDao {

    @Insert
    fun addArtist(artist: Artist) : Long

    @Query("SELECT * FROM artists WHERE name = :artistName LIMIT 1")
    fun getArtistsWithName(artistName : String) : Artist?

    @Transaction
    @Query("SELECT * FROM artists")
    fun getAllArtistsWithSongs() : List<ArtistWithSongs>
}