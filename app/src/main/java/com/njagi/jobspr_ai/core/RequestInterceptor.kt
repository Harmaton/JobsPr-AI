package com.njagi.jobspr_ai.core

import okhttp3.Interceptor

class OpenAIRequestInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "Bearer " + CONSTANTS.OPENAPIKEY)
            .build()
        return chain.proceed(request)
    }
}
