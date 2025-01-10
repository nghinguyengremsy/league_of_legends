package com.example.leagueoflegends.data.repository

import com.example.leagueoflegends.data.source.ChampionSource
import com.example.leagueoflegends.domain.model.ChampionResponseModel
import com.example.leagueoflegends.domain.repository.ChampionRepository
import com.skydoves.sandwich.ApiResponse

class ChampionRepositoryImpl(private  val source: ChampionSource): ChampionRepository {
    override suspend fun getAllChampions(): ApiResponse<ChampionResponseModel> = source.getAllChampions()

    override suspend fun getChampionByName(name: String): ApiResponse<ChampionResponseModel> = source.getChampionByName(name)
}