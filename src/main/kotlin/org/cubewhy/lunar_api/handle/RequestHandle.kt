package org.cubewhy.lunar_api.handle

import io.ktor.websocket.*

data class RequestHandle(
    val requestId: ByteArray,
    val methodName: String,
    val payload: ByteArray,

    private val session: WebSocketSession, // services should not access this
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RequestHandle

        if (!requestId.contentEquals(other.requestId)) return false
        if (methodName != other.methodName) return false
        if (!payload.contentEquals(other.payload)) return false
        if (session != other.session) return false

        return true
    }

    override fun hashCode(): Int {
        var result = requestId.contentHashCode()
        result = 31 * result + methodName.hashCode()
        result = 31 * result + payload.contentHashCode()
        result = 31 * result + session.hashCode()
        return result
    }
}
