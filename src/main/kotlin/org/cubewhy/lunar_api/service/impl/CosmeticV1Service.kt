package org.cubewhy.lunar_api.service.impl

import org.cubewhy.lunar_api.entity.CosmeticSettings
import org.cubewhy.lunar_api.entity.RpcResponse
import org.cubewhy.lunar_api.entity.toRpcResponse
import org.cubewhy.lunar_api.factory.CosmeticV1Factory
import org.cubewhy.lunar_api.handle.RequestHandle
import org.cubewhy.lunar_api.service.RpcService

class CosmeticV1Service : RpcService {
    override fun serviceName() = "lunarclient.websocket.cosmetic.v1.CosmeticService"

    override suspend fun handleRequest(handle: RequestHandle): RpcResponse? {
        val method = handle.methodName
        return when (method) {
            "Login" -> handleLoginRequest(handle)
            else -> null // not implemented yet
        }
    }

    suspend fun handleLoginRequest(handle: RequestHandle): RpcResponse {
        return CosmeticV1Factory.loginResponseWithAllCosmetics(
            CosmeticSettings() // now we use the default, we'll support config persisting later
        ).toRpcResponse()
    }
}