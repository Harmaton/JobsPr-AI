package com.njagi.jobspr_ai.network

import retrofit2.http.GET

interface Jobapi {

    @GET()
    suspend fun SearchDescription() {

    }

}