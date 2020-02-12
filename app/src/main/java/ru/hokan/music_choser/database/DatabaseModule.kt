package ru.hokan.music_choser.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import kotlin.concurrent.thread

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun getDatabase(context: Context): Database {
        return Room.databaseBuilder(
            context,
            Database::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    companion object {
        const val DATABASE_NAME = "musicChoser"
    }
}