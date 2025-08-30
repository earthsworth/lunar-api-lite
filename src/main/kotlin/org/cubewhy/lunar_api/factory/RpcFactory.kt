package org.cubewhy.lunar_api.factory

import com.google.protobuf.ByteString
import com.google.protobuf.kotlin.toByteString
import com.lunarclient.websocket.protocol.v1.ClientboundWebSocketMessage
import com.lunarclient.websocket.protocol.v1.WebSocketRpcResponse

object RpcFactory {
    fun clientboundMessageWithResponse(payload: ByteArray, requestId: ByteString): ClientboundWebSocketMessage =
        ClientboundWebSocketMessage.newBuilder().apply {
            rpcResponse = websocketRpcResponse(payload, requestId)
        }.build()

    fun websocketRpcResponse(payload: ByteArray, requestId: ByteString): WebSocketRpcResponse =
        WebSocketRpcResponse.newBuilder().apply {
            this.requestId = requestId
            this.output = payload.toByteString()
        }.build()
}