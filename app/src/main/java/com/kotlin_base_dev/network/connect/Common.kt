package com.kotlin_base_dev.network.connect

object Common {
    private val BASE_URL = "https://www.simplifiedcoding.net/demos/"
    val retrofitService: MainInterface
        get() = RetrofitClient.getClient(BASE_URL).create(MainInterface::class.java)
}