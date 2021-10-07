package com.delug3.marvelapp.utils.workers

import android.app.Application
import com.delug3.marvelapp.data.persistence.AppDatabase
import com.delug3.marvelapp.data.repository.CharactersDatabaseDataSource

class CharactersApplication : Application() {
        // Using by lazy so the database and the repository are only created when they're needed
        // rather than when the application starts
        val database by lazy { AppDatabase.getDatabase(this) }
        val repository by lazy { CharactersDatabaseDataSource(database.charactersDao()) }
}
