package org.cubewhy.lunar_api.factory

import com.lunarclient.common.v1.Color

object CommonV1Factory {
    fun colorFromHex(hex: Int): Color = Color.newBuilder()
        .setColor(hex)
        .build()
}