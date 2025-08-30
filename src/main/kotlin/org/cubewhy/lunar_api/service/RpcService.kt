package org.cubewhy.lunar_api.service

import org.cubewhy.lunar_api.entity.RpcResponse
import org.cubewhy.lunar_api.handle.RequestHandle

interface RpcService {
    fun serviceName(): String

    suspend fun handleRequest(handle: RequestHandle): RpcResponse?
}