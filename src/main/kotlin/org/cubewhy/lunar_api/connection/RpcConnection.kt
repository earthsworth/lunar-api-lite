package org.cubewhy.lunar_api.connection

import com.lunarclient.websocket.protocol.v1.ServerboundWebSocketMessage
import io.ktor.websocket.*
import org.cubewhy.lunar_api.handle.RequestHandle
import org.cubewhy.lunar_api.service.RpcService
import org.cubewhy.lunar_api.service.impl.CosmeticV1Service

class RpcConnection(private val session: WebSocketSession) {

    private var hasProcessedHandshake = false

    companion object {
        val services = mutableMapOf<String, RpcService>()

        private fun registerService(service: RpcService) {
            services[service.serviceName()] = service
        }

        init {
            // register handlers
            registerService(CosmeticV1Service())
        }
    }

    suspend fun handleFrame(payload: ByteArray) {
        if (!hasProcessedHandshake) {
            hasProcessedHandshake = true
            return // ignore the handshake message because we don't need it
        }
        val message = ServerboundWebSocketMessage.parseFrom(payload)
        this.handleMessage(message)
    }

    suspend fun handleMessage(message: ServerboundWebSocketMessage) {
        val requestId = message.requestId
        val serviceName = message.service
        val methodName = message.method
        val payload = message.input

        // find the handler
        val handler = services[serviceName] ?: return

        // handle request
        val response = handler.handleRequest(
            RequestHandle(
                requestId.toByteArray(),
                methodName,
                payload.toByteArray(),
                session
            )
        ) ?: return

        // send back the response
        response.sendWith(this.session, requestId)
    }
}