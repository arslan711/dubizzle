package com.dubizzle.assignment.repository.remote

import com.dubizzle.assignment.utils.auxilary.REQUEST_TIMEOUT
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object OkHttpProvider {


  private var okHttpClient: OkHttpClient? = null

  fun getOkHttpClient(): OkHttpClient {
    return if (okHttpClient == null) {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

      val okHttpClient = OkHttpClient.Builder()
          .addInterceptor(logging)
          .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
          .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
          .build()
      OkHttpProvider.okHttpClient = okHttpClient
      okHttpClient
    } else {
      okHttpClient!!
    }
  }
}