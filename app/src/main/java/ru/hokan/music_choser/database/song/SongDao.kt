package ru.hokan.music_choser.database.song

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface SongDao {

    @Insert
    fun addSong(song: Song)

    @Query("SELECT * FROM songs")
    fun getAllSongs() : List<Song>

    @Transaction
    @Query("SELECT * FROM songs")
    fun getAllSongsWithArtist() : List<SongWithArtist>
}