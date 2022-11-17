package cn.fadinglight.routes

import cn.fadinglight.libs.Resp
import cn.fadinglight.services.BillServiceImpl
import cn.fadinglight.vo.BillVO
import cn.fadinglight.vo.bill
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.util.*

fun Route.billRoute() {
    val billService = BillServiceImpl()
    route("/bill") {
        get("/{year}/{month}") {
            runCatching {
                val year = call.parameters["year"]!!
                val month = call.parameters["month"]!!
                billService.getManyBills(year, month)
            }.onSuccess {
                call.respond(Resp.Ok(it).json())
            }.onFailure {
                call.respond(Resp.Error(it.message, code = -1).json())
            }
        }

        get("/") {
            call.respond(status = HttpStatusCode.OK, Resp.Ok(billService.getManyBills()).json())
        }

        post("/") {
            runCatching {
                val bills = call.receive<List<BillVO>>().map(BillVO::bill)
                billService.addManyBills(bills)
            }.onSuccess {
                call.respond(Resp.Ok(it).json())
            }.onFailure {
                call.respond(Resp.Error(it.message, code = -1).json())
            }
        }

        delete("{id}") {
            val id = call.parameters.getOrFail("id").toInt()
            val count = billService.deleteOneBill(id)
            call.respond(status = HttpStatusCode.OK, Resp.Ok(count).json())
        }

        put("/") {
            val bill = call.receive<BillVO>().bill()
            val count = billService.updateOneBill(bill)
            call.respond(status = HttpStatusCode.OK, Resp.Ok(count).json())
        }
    }
}