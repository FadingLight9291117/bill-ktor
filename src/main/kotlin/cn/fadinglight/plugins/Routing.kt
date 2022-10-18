package cn.fadinglight.plugins

import cn.fadinglight.routes.billRoute
import cn.fadinglight.routes.labelRoute
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        route("/") {
            get {
                call.respondText("Hello World!")
            }
        }
        route("/api/v1") {
            billRoute()
            labelRoute()
        }
    }
}
