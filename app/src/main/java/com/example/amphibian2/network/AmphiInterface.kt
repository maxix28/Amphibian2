package com.example.amphibian2.network

import retrofit2.http.GET

interface AmphiInterfaceApi {
    @GET("amphibians")
    suspend fun getAmphibians (): List<AmphibiansItem>
}