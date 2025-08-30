package org.cubewhy.lunar_api.utils

import com.google.protobuf.GeneratedMessage
import io.ktor.websocket.*

suspend fun WebSocketSession.send(message: GeneratedMessage) {
    this.send(message.toByteArray())
}