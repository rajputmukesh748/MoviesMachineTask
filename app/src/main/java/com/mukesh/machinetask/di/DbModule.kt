package com.mukesh.machinetask.di

import android.app.Application
import androidx.room.Room
import com.mukesh.machinetask.common.STRING_ALIAS
import com.mukesh.machinetask.db.MachineTaskDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    /**
     * Provide Data Base Name
     * */
    @Provides
    @Singleton
    fun dbName(application: Application): String = application.applicationContext.getString(STRING_ALIAS.app_name).replace(" ", "_")


    /**
     * Provide Data Base Singleton Instance
     * */
    @Provides
    @Singleton
    fun provideDataBase(application: Application, dbName: String): MachineTaskDb =
        Room.databaseBuilder(application, MachineTaskDb::class.java, dbName).fallbackToDestructiveMigration().build()


}