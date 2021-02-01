package com.dubizzle.assignment.repository.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dubizzle.assignment.repository.local.database.converters.CustomConverter
import com.dubizzle.assignment.repository.local.database.dao.DubizzleDAO
import com.dubizzle.assignment.repository.local.database.pojo.Dubizzle
import com.dubizzle.assignment.utils.auxilary.ROOM_DB_NAME


@Database(entities = [Dubizzle::class], version = 1, exportSchema = false)
@TypeConverters(CustomConverter::class)
abstract class DubizzleRoomDB : RoomDatabase() {

    abstract fun getDubizzleDAO(): DubizzleDAO

    companion object {
        private var INSTANCE: DubizzleRoomDB? = null

        fun getDatabase(context: Context) = INSTANCE ?: kotlin.run {
            Room.databaseBuilder(
                context.applicationContext,
                DubizzleRoomDB::class.java,
                ROOM_DB_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
