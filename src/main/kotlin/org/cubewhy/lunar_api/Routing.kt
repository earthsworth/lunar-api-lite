package org.cubewhy.lunar_api

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Powered by https://lunarclient.top")
        }
    }
}
