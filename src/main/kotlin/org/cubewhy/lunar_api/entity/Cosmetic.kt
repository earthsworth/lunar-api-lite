package org.cubewhy.lunar_api.entity

import java.time.Instant

data class CosmeticSettings(
    var lunarLogoColor: LunarLogoColor = LunarLogoColor.WHITE,
    var lunarPlusColor: LunarPlusColor = LunarPlusColor.GREEN,
    var lunarPlusState: Boolean = true,
    var clothCloak: Boolean = true,

    var flipShoulderPet: Boolean = false,
    var activeCosmetics: List<Int> = mutableListOf(),
    var showHatsOverHelmet: Boolean = false,
    var showHatsOverSkinLayer: Boolean = false,
    var hatHeightOffsetCount: Int = 0,
    var showOverChestplate: Boolean = false,
    var showOverBoots: Boolean = false,
    var showOverLeggings: Boolean = false,
    var equippedCosmetics: List<LunarCosmetic> = emptyList(),
    var logoAlwaysShow: Boolean = true
)

data class Cosmetic(
    val cosmeticId: Int,
    val name: String,
) {
    fun toUserCosmetic() = LunarCosmetic(
        cosmeticId = cosmeticId,
        grantedAt = Instant.now(),
        expiresAt = null,
        expirationReason = null,
    )
}

data class LunarCosmetic(
    val cosmeticId: Int,
    val grantedAt: Instant,
    val expiresAt: Instant?,
    val expirationReason: CosmeticExpirationReason?
)

enum class CosmeticExpirationReason {
    UNSPECIFIED,
    TIME_LAPSED,
    LEFT_LC_DISCORD;
}
