package com.example.amphibian2.data

import com.example.amphibian2.network.AmphiInterfaceApi
import com.example.amphibian2.network.AmphibiansItem

interface AmphibiansRepository {
    suspend fun getAmphibians(): List<AmphibiansItem>
}

class NetworkAmpiRepository(private val ampiApiService: AmphiInterfaceApi): AmphibiansRepository{
    override suspend fun getAmphibians(): List<AmphibiansItem> =ampiApiService.getAmphibians()

}