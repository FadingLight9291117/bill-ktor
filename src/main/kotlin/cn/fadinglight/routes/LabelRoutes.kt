package cn.fadinglight.routes

import cn.fadinglight.libs.Resp
import cn.fadinglight.services.LabelServiceImpl
import cn.fadinglight.vo.LabelVO
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.labelRoute() {
    val labelService = LabelServiceImpl()
    route("/label") {
        get("/") {
            call.respond(
                Resp.Ok(
//                    mapOf("consume" to listOf<LabelVO>(), "income" to listOf<LabelVO>())
                    labelService.getLabels()
                ).json()
            )
        }
        post {}
    }
}