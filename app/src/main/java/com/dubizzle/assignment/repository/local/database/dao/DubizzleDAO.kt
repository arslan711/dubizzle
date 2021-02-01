package com.dubizzle.assignment.repository.local.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dubizzle.assignment.repository.local.database.pojo.Dubizzle

@Dao
interface DubizzleDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDubizzle(dubizzle: Dubizzle)

    @Delete
    fun deleteDubizzle(dubizzle: Dubizzle)

    @Query("SELECT * FROM dubizzle_table")
     fun getAllDubizzles(): LiveData<List<Dubizzle>>

    @Query("SELECT * FROM dubizzle_table WHERE id = :dubizzleId")
     fun getDubizzleById(dubizzleId: String): LiveData<Dubizzle?>
}
