package cn.fadinglight.routes

import io.ktor.server.routing.*


fun Route.billRoute() {
    route("/bill") {
        get("/") {}
        get("/{year}/{month}") {}
        post("/create") {}
        delete("/{year}/{month}/{day}") {}
    }
}