package com.example.mesibikes.application

import android.app.Application
import androidx.room.Room
import com.example.mesibikes.bikeViewModelModule
import com.example.mesibikes.db.BikeDatabase
import com.example.mesibikes.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

class App : Application() {

    @Override
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(appModule, repositoryModule, bikeViewModelModule)
        }
    }

    private val appModule = module {
        single {
            Room
                .databaseBuilder(applicationContext, BikeDatabase::class.java, "bikes-db")
                .build()
                .bikeDao()
        }
    }
}