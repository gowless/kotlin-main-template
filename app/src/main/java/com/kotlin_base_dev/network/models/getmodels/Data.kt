package com.kotlin_base_dev.network.models.getmodels

data class Data(
    val __v: Int,
    val _id: String,
    val appId: String,
    val categories: List<Category>,
    val listoffers: List<Listoffers>
)