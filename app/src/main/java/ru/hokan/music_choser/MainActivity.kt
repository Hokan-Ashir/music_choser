package ru.hokan.music_choser

import android.os.AsyncTask
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.music_choser.R
import ru.hokan.music_choser.database.Database
import ru.hokan.music_choser.database.artist.Artist
import ru.hokan.music_choser.database.song.Song
import ru.hokan.music_choser.di.MusicChoserApplication
import ru.hokan.music_choser.ui.main.MainFragment
import ru.hokan.music_choser.ui.songs.add.AddSongFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var database: Database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        (application as MusicChoserApplication).appComponent.inject(this)
        // TODO add check for savedInstance
        supportFragmentManager.beginTransaction()
            .replace(R.id.background_layout, AsyncFragmentStartSelector(database).execute().get())
            .commitNow()
    }

    class AsyncFragmentStartSelector(val database: Database) : AsyncTask<Void, Void, Fragment>() {
        override fun doInBackground(vararg params: Void?): Fragment {
            val isAnySongsExist = database.getSongDao().getAllSongs().isNotEmpty()
            return if (isAnySongsExist) {
                MainFragment.newInstance()
            } else {
                createPackOfSongs(1000)
                AddSongFragment.newInstance()
            }
        }

        private fun createPackOfSongs(numberOfSongsToAdd : Int) {
            val artistId = database.getArtistDao().addArtist(Artist(null, "artist"))
            (0..numberOfSongsToAdd).forEach {
                database.getSongDao().addSong(Song(null, "song$it", artistId))
            }
        }
    }
}
