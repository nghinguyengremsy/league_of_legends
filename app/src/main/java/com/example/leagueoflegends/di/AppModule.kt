package com.example.leagueoflegends.di

import com.example.leagueoflegends.data.repository.ChampionRepositoryImpl
import com.example.leagueoflegends.data.source.ChampionSource
import com.example.leagueoflegends.data.source.ChampionSourceRemote
import com.example.leagueoflegends.domain.repository.ChampionRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient = HttpClient(OkHttp.create()) {
        defaultRequest {
            url(ChampionSourceRemote.baseUrl)
            header(HttpHeaders.ContentType,"application/json")
        }
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys= true
                }
            )
        }
    }
    @Provides
    @Singleton

    fun provideChampionSource(httpClient: HttpClient): ChampionSource= ChampionSourceRemote(httpClient)

    @Provides
    @Singleton
    fun provideChampionRepository(source: ChampionSource): ChampionRepository = ChampionRepositoryImpl(source)
}