package cn.fadinglight.plugins

import cn.fadinglight.Bills
import cn.fadinglight.Labels
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

const val HIKARI_CONFIG_KEY = "ktor.hikariConfig"

fun Application.configureDatabase() {
    val configPath = environment.config.property(HIKARI_CONFIG_KEY).getString()
    val dbConfig = HikariConfig(configPath)
    val dataSource = HikariDataSource(dbConfig)
    Database.connect(dataSource)
    createTables()
    log.info("Initialized database")
}

private fun createTables() = transaction {
    SchemaUtils.create(
        Bills,
        Labels,
    )
}

