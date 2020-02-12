package ru.hokan.music_choser.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.hokan.music_choser.database.artist.Artist
import ru.hokan.music_choser.database.artist.ArtistDao
import ru.hokan.music_choser.database.song.Song
import ru.hokan.music_choser.database.song.SongDao

@Database(entities = [Artist::class, Song::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun getArtistDao() : ArtistDao
    abstract fun getSongDao() : SongDao
}