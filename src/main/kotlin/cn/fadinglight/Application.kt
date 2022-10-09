package cn.fadinglight

import cn.fadinglight.plugins.*
import io.ktor.server.application.*


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    configureAdministration()
    configureSerialization()
    configureHTTP()
    configureLogging()
    configureRouting()
    configureSession()
    configureWebSocket()
    configureDatabase()
}