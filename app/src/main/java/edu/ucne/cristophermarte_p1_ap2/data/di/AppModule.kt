package edu.ucne.cristophermarte_p1_ap2.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.ucne.cristophermarte_p1_ap2.data.local.database.CristopherMarteP1Db
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideContexto(@ApplicationContext appContext: Context): CristopherMarteP1Db{
        return Room.databaseBuilder(
            appContext, CristopherMarteP1Db::class.java, "CristopherMarteP1Db"
        ).fallbackToDestructiveMigration().build()
    }
    @Provides
    fun provideSistemaDao(sistemadDb: CristopherMarteP1Db) = sistemadDb.sistemaDao()
}