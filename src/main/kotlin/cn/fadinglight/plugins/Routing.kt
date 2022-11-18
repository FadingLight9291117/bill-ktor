package cn.fadinglight.plugins

import cn.fadinglight.models.LabelType
import cn.fadinglight.routes.billRoute
import cn.fadinglight.routes.labelRoute
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        route("/") {
            get {
                call.respond(LabelType.LABEL)
            }
        }
        route("/api/v1") {
            billRoute()
            labelRoute()
        }
    }
}
