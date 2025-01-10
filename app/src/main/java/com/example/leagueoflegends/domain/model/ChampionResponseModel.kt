package com.example.leagueoflegends.domain.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChampionResponseModel(
    @SerialName("data")
    val champion: Map<String,ChampionModel> = emptyMap(),
    @SerialName("format")
    val format: String? = "",
    @SerialName("type")
    val type: String? = "",
    @SerialName("version")
    val version: String? = ""
)