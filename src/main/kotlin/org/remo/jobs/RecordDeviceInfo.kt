package org.remo.jobs

import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.features.json.JsonFeature
import kotlinx.coroutines.runBlocking
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.remo.external.Env.NATURE_REMO.Companion.API_ENDPOINT
import org.remo.external.Logger
import org.remo.gateways.api.natureremo.NatureAPIClient
import org.remo.gateways.repositories.SensorRecordsRepository

class RecordDeviceInfo() {
    private val repository = SensorRecordsRepository()
    private val client = NatureAPIClient(
        API_ENDPOINT,
        HttpClient(Apache) {
            install(JsonFeature)
        }
    )

    fun execute() {
        Logger.info("start record device info job.")
        val response = runBlocking {
            client.get1Devices()
        }
        transaction {
            addLogger(StdOutSqlLogger)
            repository.insertList(response)
        }
        Logger.info("finish record device info job.")
    }
}
