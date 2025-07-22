package com.ismael.amphibians.network

import com.ismael.amphibians.model.Amphibian
import retrofit2.http.GET

interface AmphibianApiService {

    @GET("amphibians")
    suspend fun getAmphibians(): List<Amphibian>
}