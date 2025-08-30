package org.cubewhy.lunar_api.entity

import com.google.protobuf.ByteString
import com.google.protobuf.GeneratedMessage
import io.ktor.websocket.*
import org.cubewhy.lunar_api.factory.RpcFactory
import org.cubewhy.lunar_api.utils.send

data class RpcResponse(
    val payload: ByteArray
) {
    companion object {
        fun create(message: GeneratedMessage) = RpcResponse(message.toByteArray())
    }

    suspend fun sendWith(session: WebSocketSession, requestId: ByteString) {
        session.send(RpcFactory.clientboundMessageWithResponse(this.payload, requestId))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RpcResponse

        return payload.contentEquals(other.payload)
    }

    override fun hashCode(): Int {
        return payload.contentHashCode()
    }
}

fun GeneratedMessage.toRpcResponse(): RpcResponse =
    RpcResponse.create(this)
