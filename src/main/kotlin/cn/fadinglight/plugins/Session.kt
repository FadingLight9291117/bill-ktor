package cn.fadinglight.plugins

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*

fun Application.configureSession() {
    install(Sessions) {
        cookie<UserSession>("user_session", SessionStorageMemory()) {
            cookie.path = "/"
            cookie.maxAgeInSeconds = 10
        }
    }
    routing {
        get("/sessions") {
            call.sessions.set(UserSession(id = "0", count = 0))
            call.respondText("Hello world!")
        }
    }
}

data class UserSession(val id: String, val count: Int)
