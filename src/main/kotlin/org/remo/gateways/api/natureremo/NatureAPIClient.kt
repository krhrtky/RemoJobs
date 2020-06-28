package org.remo.gateways.api.natureremo

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import org.remo.external.Env.NATURE_REMO.Companion.API_AUTH_KEY
import org.remo.gateways.api.natureremo.model.Device

open class NatureAPIClient(private val endpoint: String, private val client: HttpClient = HttpClient()) {
    /**
     * Fetch the list of Remo devices the user has access to.
     *
     * @return List of Remo devices
     */
    suspend fun get1Devices(
    ) = runCatching {
        client.get<List<Device>>("$endpoint/1/devices") {
            header("Authorization", "Bearer $API_AUTH_KEY")
        }
    }.fold(
        onSuccess = { it },
        onFailure = {
            it.printStackTrace()
            ArrayList()
        }
    )
}
