package ru.hokan.music_choser.database.song

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SongDao {

    @Insert
    fun addSong(song: Song) : Long

    @Query("SELECT * FROM songs")
    fun getAllSongs() : List<Song>

    @Query("SELECT * FROM songs where name = :songName LIMIT 1")
    fun getSongWithName(songName : String) : Song?
}