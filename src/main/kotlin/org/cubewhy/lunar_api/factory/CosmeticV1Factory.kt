package org.cubewhy.lunar_api.factory

import com.lunarclient.websocket.cosmetic.v1.CustomizableCosmeticSettings
import com.lunarclient.websocket.cosmetic.v1.EquippedCosmetic
import com.lunarclient.websocket.cosmetic.v1.LoginResponse
import com.lunarclient.websocket.cosmetic.v1.OwnedCosmetic
import org.cubewhy.lunar_api.entity.CosmeticExpirationReason
import org.cubewhy.lunar_api.entity.CosmeticExpirationReason.*
import org.cubewhy.lunar_api.entity.CosmeticSettings
import org.cubewhy.lunar_api.entity.LunarCosmetic
import org.cubewhy.lunar_api.entity.LunarPlusColor

object CosmeticV1Factory {
    fun loginResponseWithAllCosmetics(
        properties: CosmeticSettings
    ): LoginResponse = LoginResponse.newBuilder().apply {
        settings = cosmeticSettings(properties)
        logoColor = logoColor
        rankName = "Player"

        if (properties.lunarPlusState) {
            addAllAvailableLunarPlusColors(LunarPlusColor.entries.map { CommonV1Factory.colorFromHex(it.colorHex) })
        }
        //            addAllOwnedCosmeticIds(cosmeticList.map { it.cosmeticId })
        //            addAllOwnedCosmetics(cosmeticList.map { it.toUserCosmetic().toOwnedCosmetic() })
        logoAlwaysShow = properties.logoAlwaysShow
        // hack: use LunarClient's hasAllCosmeticsFlag
        hasAllCosmeticsFlag = true
    }.build()

    fun cosmeticSettings(properties: CosmeticSettings): CustomizableCosmeticSettings {
        return CustomizableCosmeticSettings.newBuilder().also { builder ->
            builder.addAllActiveCosmeticIds(properties.activeCosmetics.map { it })
            builder.addAllEquippedCosmetics(properties.equippedCosmetics.map { equippedCosmetic(it) })
            builder.flipShoulderPet = properties.flipShoulderPet
            builder.showHatsOverHelmet = properties.showHatsOverHelmet
            builder.showHatsOverSkinLayer = properties.showHatsOverSkinLayer
            builder.showOverChestplate = properties.showOverChestplate
            builder.showOverLeggings = properties.showOverLeggings
            builder.showOverBoots = properties.showOverBoots

            if (properties.lunarPlusState) {
                builder.clothCloak = properties.clothCloak
                builder.plusColor = CommonV1Factory.colorFromHex(properties.lunarPlusColor.colorHex)
            }
        }.build()
    }

    fun equippedCosmetic(cosmetic: LunarCosmetic): EquippedCosmetic = EquippedCosmetic.newBuilder()
        .setCosmeticId(cosmetic.cosmeticId)
        .build()

    fun ownedCosmetic(cosmetic: LunarCosmetic): OwnedCosmetic = OwnedCosmetic.newBuilder().apply {
        cosmeticId = cosmetic.cosmeticId
        grantedAt = TimestampFactory.fromInstant(cosmetic.grantedAt)
        cosmetic.expirationReason?.let { setExpirationReason(expirationReason(it)) }
        cosmetic.expiresAt?.let { setExpiresAt(TimestampFactory.fromInstant(it)) }
    }.build()

    fun expirationReason(reason: CosmeticExpirationReason): OwnedCosmetic.ExpirationReason = when (reason) {
        UNSPECIFIED -> OwnedCosmetic.ExpirationReason.EXPIRATION_REASON_UNSPECIFIED
        TIME_LAPSED -> OwnedCosmetic.ExpirationReason.EXPIRATION_REASON_TIME_LAPSED
        LEFT_LC_DISCORD -> OwnedCosmetic.ExpirationReason.EXPIRATION_REASON_LEFT_LC_DISCORD
    }
}