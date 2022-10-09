package cn.fadinglight.plugins

import io.ktor.serialization.kotlinx.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.channels.consumeEach
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.time.Duration

fun Application.configureWebSocket() {
    install(WebSockets) {
        contentConverter = KotlinxWebsocketSerializationConverter(Json)

        pingPeriod = Duration.ofSeconds(15)
        timeout = Duration.ofSeconds(15)
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }

    routing {
        webSocket("/customers/1") {
            sendSerialized(Customer(id = 1, firstName = "John", lastName = "Smith"))
            incoming.consumeEach {
                when (it) {
                    is Frame.Text -> println(it.readText())
                    else -> Unit
                }
            }
        }
    }
}

@Serializable
data class Customer(val id: Int, val firstName: String, val lastName: String)
