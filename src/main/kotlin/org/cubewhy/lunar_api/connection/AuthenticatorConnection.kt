package org.cubewhy.lunar_api.connection

import com.lunarclient.authenticator.v1.ServerboundWebSocketMessage
import io.ktor.websocket.*
import org.cubewhy.lunar_api.factory.AuthenticatorFactory
import org.cubewhy.lunar_api.utils.send

class AuthenticatorConnection(private val session: WebSocketSession) {
    suspend fun handleFrame(payload: ByteArray) {
        val request = ServerboundWebSocketMessage.parseFrom(payload)
        this.handleMessage(request)
    }

    private suspend fun handleMessage(request: ServerboundWebSocketMessage) {
        if (request.hasHello()) {
            // we don't actual care about the payload at the moment because
            // this servlet is only designed to run locally
            session.send(AuthenticatorFactory.authSuccessResponse("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWUsImlhdCI6MTUxNjIzOTAyMn0.KMUFsIDTnFmyG3nMiGM6H9FNFUROf3wh7SmqJp-QV30"))
        }
        // we don't care about authorization because this servlet only runs locally
    }
}