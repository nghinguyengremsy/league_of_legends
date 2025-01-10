package com.example.leagueoflegends.domain.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataModel(
    @SerialName("Aatrox")
    val aatrox: ChampionModel? = ChampionModel()
)