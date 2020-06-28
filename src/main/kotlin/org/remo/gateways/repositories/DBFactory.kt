package org.remo.gateways.repositories

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.remo.external.Env.DB.Companion.DRIVER_CLASS_NAME
import org.remo.external.Env.DB.Companion.PASSWORD
import org.remo.external.Env.DB.Companion.URL
import org.remo.external.Env.DB.Companion.USER_NAME
import javax.sql.DataSource

class DBFactory {
    companion object {
        fun init(dataSource: DataSource = HikariDataSource(
            HikariConfig().apply {
                jdbcUrl = URL
                driverClassName = DRIVER_CLASS_NAME
                username = USER_NAME
                password = PASSWORD
                maximumPoolSize = 10
            }
        )) {
            Database.connect(dataSource)
            transaction {
                addLogger(StdOutSqlLogger)
                SchemaUtils.create(SensorRecords)
            }
        }
    }
}

