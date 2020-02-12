package ru.hokan.music_choser.database.artist

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import javax.inject.Qualifier

@Dao
interface ArtistDao {

    @Insert
    fun addArtist(artist: Artist) : Long

    @Query("SELECT * FROM artists")
    fun getAllArtists() : List<Artist>

    @Query("SELECT * FROM artists WHERE name = :artistName LIMIT 1")
    fun getAllArtistsWithName(artistName : String) : Artist?
}