package com.example.leagueoflegends.data.source

import com.example.leagueoflegends.domain.model.ChampionResponseModel
import com.skydoves.sandwich.ApiResponse

interface ChampionSource {
    suspend fun getAllChampions(): ApiResponse<ChampionResponseModel>
    suspend fun getChampionByName(name: String): ApiResponse<ChampionResponseModel>
}