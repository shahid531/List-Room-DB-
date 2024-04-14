package com.mr.myroom.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mr.myroom.model.UsersItem

@Database(entities = [UsersItem::class], version = 1)
abstract class RoomDB : RoomDatabase() {

    abstract fun roomDAO(): RoomDAO
}