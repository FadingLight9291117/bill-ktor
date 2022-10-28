package cn.fadinglight.routes

import io.ktor.server.routing.*

fun Route.labelRoute() {
    route("/label") {
        get {}
        post {}
    }
}