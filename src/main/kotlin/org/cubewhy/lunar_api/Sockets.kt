package org.cubewhy.lunar_api

import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import org.cubewhy.lunar_api.connection.AuthenticatorConnection
import org.cubewhy.lunar_api.connection.RpcConnection
import kotlin.time.Duration.Companion.seconds

fun Application.configureSockets() {
    install(WebSockets) {
        pingPeriod = 15.seconds
        timeout = 15.seconds
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }
    routing {
        webSocket("/ws") {
            // Authenticator
            val connection = AuthenticatorConnection(this)
            for (frame in incoming) {
                if (frame is Frame.Binary) {
                    val payload = frame.readBytes()
                    connection.handleFrame(payload)
                }
            }
        }
        webSocket("/ws/game") {
            // RPC
            val connection = RpcConnection(this)
            for (frame in incoming) {
                if (frame is Frame.Binary) {
                    val payload = frame.readBytes()
                    connection.handleFrame(payload)
                }
            }
        }
    }
}
