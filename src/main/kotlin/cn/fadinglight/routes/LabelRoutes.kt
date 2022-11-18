package cn.fadinglight.routes

import cn.fadinglight.libs.Resp
import cn.fadinglight.services.LabelServiceImpl
import cn.fadinglight.vo.LabelPost
import cn.fadinglight.vo.label
import io.ktor.server.application.*
import io.ktor.server.request.*
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
        post("/") {
            runCatching {
                call.receive<LabelPost>().label()
            }.onSuccess {
                val labelId = labelService.addLabel(it)
                call.respond(Resp.Ok(labelId).json())
            }.onFailure {
                call.respond(Resp.Error(it.message).json())
            }
        }
    }
}