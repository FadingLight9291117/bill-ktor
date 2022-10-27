package cn.fadinglight.routes

import cn.fadinglight.dao.BillDao
import cn.fadinglight.dao.bill
import cn.fadinglight.libs.Resp
import cn.fadinglight.services.BillServiceImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.lang.Exception

fun Route.billRoute() {
    val billService = BillServiceImpl()
    route("/bill") {
        get("/{year}/{month}") {
            val year = call.parameters["year"]!!
            val month = call.parameters["month"]!!
            call.respond(Resp.Ok(billService.getManyBills(year, month)).json())
        }

        get {
            call.respond(status = HttpStatusCode.OK, Resp.Ok(billService.getManyBills()).json())
        }

        post {
            try {
                val bills = call.receive<List<BillDao>>().map(BillDao::bill)
                val count = billService.addManyBills(bills)
                call.respond(status = HttpStatusCode.Created, Resp.Ok(count).json())
            } catch (e: Exception) {
                call.respond(status = HttpStatusCode.Created, Resp.Error(e.message, code = -1).json())
            }
        }
        delete("{id}") {
            val id = call.parameters["id"]!!.toInt()
            val count = billService.deleteOneBill(id)
            call.respond(status = HttpStatusCode.OK, Resp.Ok(count).json())
        }
        put {
            val bill = call.receive<BillDao>().bill()
            val count = billService.updateOneBill(bill)
            call.respond(status = HttpStatusCode.OK, Resp.Ok(count).json())
        }
    }
}