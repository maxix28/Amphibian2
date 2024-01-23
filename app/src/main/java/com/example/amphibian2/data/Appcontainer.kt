package com.example.amphibian2.data

import com.example.amphibian2.network.AmphiInterfaceApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val AmphibiansRepository : AmphibiansRepository
}

class DefaultAppContainer : AppContainer{
    private val baseUrl ="https://android-kotlin-fun-mars-server.appspot.com/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    private val retrofitService : AmphiInterfaceApi by lazy {
        retrofit.create(AmphiInterfaceApi::class.java)
    }
    override val AmphibiansRepository: AmphibiansRepository by lazy{
        NetworkAmpiRepository(retrofitService)
    }
}