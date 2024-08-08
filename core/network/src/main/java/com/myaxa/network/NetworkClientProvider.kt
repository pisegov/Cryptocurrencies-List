package com.myaxa.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class NetworkClientProvider(
    baseUrl: String,
    apiKey: String,
    json: Json = Json,
) {
    companion object {
        private const val API_KEY_HEADER_NAME = "x-cg-demo-api-key"
    }

    val client = HttpClient(OkHttp) {
        defaultRequest {
            url(baseUrl)
            header(API_KEY_HEADER_NAME, apiKey)
        }

        install(Logging) {
            logger = Logger.SIMPLE
        }

        install(ContentNegotiation) {
            json(json)
        }
    }
}
