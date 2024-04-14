package com.mr.myroom.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mr.myroom.model.UsersItem

@Dao
interface RoomDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(list: List<UsersItem>)

    @Query("UPDATE list_table SET firstName=:firstName,email=:email WHERE id=:id")
    suspend fun update(id: Long, firstName: String, email: String)

    @Query("DELETE FROM list_table WHERE id =:id")
    suspend fun delete(id: Long)

    @Query("SELECT * FROM list_table")
    suspend fun getData(): List<UsersItem>
}