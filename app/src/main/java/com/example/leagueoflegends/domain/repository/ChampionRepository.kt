package com.example.leagueoflegends.domain.repository

import com.example.leagueoflegends.domain.model.ChampionResponseModel
import com.skydoves.sandwich.ApiResponse

interface ChampionRepository {
    suspend fun getAllChampions(): ApiResponse<ChampionResponseModel>
    suspend fun getChampionByName(name: String): ApiResponse<ChampionResponseModel>
}