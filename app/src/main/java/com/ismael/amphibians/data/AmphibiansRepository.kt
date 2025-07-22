package com.ismael.amphibians.data

import com.ismael.amphibians.model.Amphibian
import com.ismael.amphibians.network.AmphibianApiService

interface AmphibiansRepository{
    suspend fun getAmphibians(): List<Amphibian>
}

class NetworkAmphibiansRepository(
    private val amphibianApiService: AmphibianApiService
): AmphibiansRepository{
    override suspend fun getAmphibians(): List<Amphibian> = amphibianApiService.getAmphibians()
}