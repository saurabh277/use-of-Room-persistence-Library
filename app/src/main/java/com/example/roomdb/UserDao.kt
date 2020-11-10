package com.example.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user:User)

    @Insert
    fun insertAll(List:List<User>)

    @Delete
    fun delete(user:User)

    @Query("SELECT * FROM User")
   fun getAllUser(): LiveData<List<User>>

}