package ru.hokan.music_choser.di

import android.app.Application

class MusicChoserApplication : Application() {

    val appComponent: MusicChoserComponent by lazy {
        DaggerMusicChoserComponent.factory().create(applicationContext)
    }
}