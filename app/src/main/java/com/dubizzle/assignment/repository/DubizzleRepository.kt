package com.dubizzle.assignment.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.dubizzle.assignment.repository.local.database.DubizzleRoomDB
import com.dubizzle.assignment.repository.local.database.dao.DubizzleDAO
import com.dubizzle.assignment.repository.local.database.pojo.Dubizzle
import com.dubizzle.assignment.repository.remote.DubizzleWebApisInterface
import com.dubizzle.assignment.repository.remote.RestClient


class DubizzleRepository private constructor(application: Application) {

    private val localSource: DubizzleDAO = DubizzleRoomDB.getDatabase(application).getDubizzleDAO()
    private val remoteSource = RestClient.getInstance().create(DubizzleWebApisInterface::class.java)



    private  fun insertDubizzle(dubizzle: Dubizzle) {
        localSource.insertDubizzle(dubizzle)
    }



    suspend fun getDubizzlesLiveData(): LiveData<List<Dubizzle>> {
        return localSource.getAllDubizzles().also {
            getDubizzlesDataRemoteSource()
        }
    }

    private suspend fun getDubizzlesDataRemoteSource() {
        try {
            val result = remoteSource.getDubizzlesData()
            result.dubizzles.forEach {
                insertDubizzle(it)
            }
        } catch (exception: Throwable) {
            println(exception.message)
        }
    }

     fun getDubizzleByIdLiveData(dubizzleId: String): LiveData<Dubizzle?> {
        return localSource.getDubizzleById(dubizzleId)
    }

    companion object {

        private var INSTANCE: DubizzleRepository? = null


        fun getInstance(application: Application): DubizzleRepository = INSTANCE ?: kotlin.run {
            INSTANCE = DubizzleRepository(application = application)
            INSTANCE!!
        }
    }
}
