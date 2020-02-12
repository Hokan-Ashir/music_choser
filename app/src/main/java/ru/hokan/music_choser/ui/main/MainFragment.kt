package ru.hokan.music_choser.ui.main

import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.fragment.app.Fragment
import com.example.music_choser.R
import kotlinx.android.synthetic.main.main_fragment.*
import ru.hokan.music_choser.database.Database
import ru.hokan.music_choser.database.song.SongWithArtist
import ru.hokan.music_choser.di.MusicChoserApplication
import ru.hokan.music_choser.ui.songs.add.AddSongFragment
import javax.inject.Inject
import kotlin.random.Random


class MainFragment : Fragment() {

    @Inject
    lateinit var database: Database

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity!!.application as MusicChoserApplication).appComponent.inject(this)
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)
        populatePickersWithData()

        add_song_button.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.background_layout, AddSongFragment.newInstance())
                .commit()
        }

        randomly_choose_song_button.setOnClickListener {
            // cause song and artists picker are having 1 to 1 relationship, we can take
            // number and position of only one picker
            val randomSongPosition = Random.nextInt(0, song_picker.displayedValues.size - 1)
            val currentSongPickerPosition = song_picker.value
            val positionsDifference = currentSongPickerPosition - randomSongPosition
            NumberPickerIncrementer(song_picker, positionsDifference).execute()
            NumberPickerIncrementer(artist_picker, positionsDifference).execute()
        }
    }

    private fun populatePickersWithData() {
        val allSongs = SelectingSongsAsyncTask(database).execute().get()
        val displayedSongs = allSongs.map { it.name!! }.toTypedArray()
        setPickerData(song_picker, displayedSongs)
        val displayedArtists = allSongs.map { it.artists!!.first().name }.toTypedArray()
        setPickerData(artist_picker, displayedArtists)
    }

    private fun setPickerData(picker: NumberPicker, displayedData: Array<String>) {
        picker.displayedValues = displayedData
        picker.minValue = 0
        picker.maxValue = artist_picker.displayedValues.size - 1
    }

    // TODO this should be easier
    class SelectingSongsAsyncTask(val database: Database) :
        AsyncTask<Void, Void, List<SongWithArtist>>() {
        override fun doInBackground(vararg params: Void?): List<SongWithArtist> {
            val songDao = database.getSongDao()
            return songDao.getAllSongsWithArtist()
        }
    }
}
