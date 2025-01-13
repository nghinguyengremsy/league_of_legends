package com.example.leagueoflegends.data.source

import com.example.leagueoflegends.domain.model.ChampionResponseModel
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.ktor.getApiResponse
import io.ktor.client.HttpClient


class ChampionSourceRemote(private val httpClient: HttpClient) : ChampionSource {
    companion object {
        const val baseUrl = "https://ddragon.leagueoflegends.com/cdn/14.23.1/data/en_US/"


    }


    override suspend fun getAllChampions(): ApiResponse<ChampionResponseModel> =
        httpClient.getApiResponse("champion.json")

    override suspend fun getChampionByName(name: String): ApiResponse<ChampionResponseModel> =
        httpClient.getApiResponse<ChampionResponseModel>("champion/$name.json")

}