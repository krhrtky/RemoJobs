package org.remo

import org.remo.external.Logger
import org.remo.jobs.RecordDeviceInfo
import org.remo.gateways.repositories.DBFactory


fun main() {
    Logger.info("start record device info.")
    Logger.info("connect DB.")
    DBFactory.init()
    RecordDeviceInfo().execute()
    Logger.info("finish record device info.")
}
