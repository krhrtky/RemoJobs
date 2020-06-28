package org.remo.gateways.repositories

import org.jetbrains.exposed.sql.insert
import org.joda.time.DateTime
import org.remo.external.Env
import org.remo.gateways.api.natureremo.model.Device
import java.util.UUID

class SensorRecordsRepository {

    fun insertList(devices: List<Device>) = run {
        devices.forEach {
            insert(it)
        }
    }

    fun insert(device: Device) = run {
        SensorRecords.insert {
            it[id] = UUID.randomUUID().toString()
            it[temperature] = device.newest_events.te.`val`
            it[humidity] = device.newest_events.hu.`val`
            it[illuminance] = device.newest_events.il.`val`
            it[createdAt] = DateTime.now()
        }
    }
}
