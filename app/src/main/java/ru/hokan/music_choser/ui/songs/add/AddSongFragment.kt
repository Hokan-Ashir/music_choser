package ru.hokan.music_choser.ui.songs.add

import android.os.AsyncTask
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.music_choser.R
import kotlinx.android.synthetic.main.add_song_fragment.*
import ru.hokan.music_choser.database.Database
import ru.hokan.music_choser.database.artist.Artist
import ru.hokan.music_choser.database.song.Song
import ru.hokan.music_choser.di.MusicChoserApplication
import ru.hokan.music_choser.ui.main.MainFragment
import javax.inject.Inject

class AddSongFragment : Fragment() {

    @Inject
    lateinit var database: Database

    companion object {
        fun newInstance() = AddSongFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity!!.application as MusicChoserApplication).appComponent.inject(this)
        return inflater.inflate(R.layout.add_song_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        submit_add_song_button.isEnabled = isSubmitButtonShouldBeEnabled()
        addTextWatchers()
        addCommitSongListener()
    }

    private fun addTextWatchers() {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                submit_add_song_button.isEnabled = isSubmitButtonShouldBeEnabled()
            }

        }

        add_song_artist_name.addTextChangedListener(textWatcher)
        add_song_song_name.addTextChangedListener(textWatcher)
    }

    private fun isSubmitButtonShouldBeEnabled(): Boolean {
        val formFields =
            listOf(add_song_artist_name.text.toString(), add_song_song_name.text.toString())
        return formFields.filter { it.isNotEmpty() }.size == formFields.size
    }

    private fun addCommitSongListener() {
        submit_add_song_button.setOnClickListener {
            val artistDao = database.getArtistDao()
            val songDao = database.getSongDao()
            AsyncTask.execute {
                val artistName = add_song_artist_name.text.toString()
                val existedArtist = artistDao.getAllArtistsWithName(artistName)
                val artistId = if (existedArtist == null) {
                    artistDao.addArtist(Artist(null, artistName))
                } else {
                    existedArtist.id
                }

                val songName = add_song_song_name.text.toString()
                songDao.addSong(Song(null, songName, artistId))

                parentFragmentManager.beginTransaction()
                    .replace(R.id.background_layout, MainFragment.newInstance())
                    .commit()
            }
        }
    }
}