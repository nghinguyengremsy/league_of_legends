package com.example.leagueoflegends.domain.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChampionModel(
    @SerialName("id")
    val id: String? = "",
    @SerialName("image")
    val image: ImageModel? = ImageModel(),
    @SerialName("key")
    val key: String? = "",
    @SerialName("lore")
    val lore: String? = "",
    @SerialName("name")
    val name: String? = "",
    @SerialName("passive")
    val passive: PassiveModel? = PassiveModel(),
    @SerialName("spells")
    val spells: List<SpellModel> = emptyList(),
    @SerialName("tags")
    val tags: List<String> = emptyList(),
    @SerialName("title")
    val title: String? = ""
)