package ru.hokan.music_choser.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.hokan.music_choser.MainActivity
import ru.hokan.music_choser.ui.main.MainFragment
import ru.hokan.music_choser.database.DatabaseModule
import ru.hokan.music_choser.ui.songs.add.AddSongFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [DatabaseModule::class])
abstract class MusicChoserComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): MusicChoserComponent
    }

    abstract fun inject(musicFragment: MainFragment)
    abstract fun inject(addSongFragment: AddSongFragment)
    abstract fun inject(mainActivity: MainActivity)
}