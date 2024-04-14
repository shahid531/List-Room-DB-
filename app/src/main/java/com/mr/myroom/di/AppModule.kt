package com.mr.myroom.di

import android.content.Context
import androidx.room.Room
import com.mr.myroom.room.RoomDAO
import com.mr.myroom.room.RoomDB
import com.mr.myroom.service.RoomApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRepository(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        return Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

    }

    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): RoomApi {
        return retrofit.create(RoomApi::class.java)
    }

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): RoomDB {
        return Room.databaseBuilder(context, RoomDB::class.java, name = "list_table").build()
    }

    @Singleton
    @Provides
    fun provideDAO(roomDB: RoomDB): RoomDAO {
        return roomDB.roomDAO()
    }
}