package com.dubizzle.assignment.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.dubizzle.assignment.repository.DubizzleRepository
import com.dubizzle.assignment.repository.local.database.pojo.Dubizzle
import kotlinx.coroutines.Dispatchers

class DetailViewModel(application: Application) : AndroidViewModel(application) {

    private val dubizzleRepository = DubizzleRepository.getInstance(application)

    fun dubizzleLiveData(id: String): LiveData<Dubizzle?> = liveData(Dispatchers.IO) {
        emitSource(dubizzleRepository.getDubizzleByIdLiveData(id))
    }
}
