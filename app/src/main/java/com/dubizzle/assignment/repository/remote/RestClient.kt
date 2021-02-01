package com.dubizzle.assignment.repository.remote
import com.dubizzle.assignment.utils.auxilary.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestClient {

    private var INSTANCE: Retrofit? = null



    fun getInstance(): Retrofit = INSTANCE ?: kotlin.run {



        val okClient = OkHttpProvider.getOkHttpClient()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
