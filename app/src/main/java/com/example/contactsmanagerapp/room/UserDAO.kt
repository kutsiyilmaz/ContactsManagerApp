package com.example.contactsmanagerapp.room

import android.os.FileObserver.DELETE
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDAO {
    @Insert
    suspend fun insertUser(user: User):Long

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM user")
    suspend fun deleteAll()

    @Query("SELECT * FROM user")
    fun getAllUsersInDB(): LiveData<List<User>>



}