package org.cubewhy.lunar_api.factory

import com.lunarclient.authenticator.v1.AuthSuccessMessage
import com.lunarclient.authenticator.v1.ClientboundWebSocketMessage

object AuthenticatorFactory {
    fun authSuccessResponse(token: String): ClientboundWebSocketMessage =
        ClientboundWebSocketMessage.newBuilder().apply {
            authSuccess = this@AuthenticatorFactory.authSuccess(token)
        }.build()

    fun authSuccess(token: String): AuthSuccessMessage = AuthSuccessMessage.newBuilder().apply {
        jwt = token
    }.build()
}