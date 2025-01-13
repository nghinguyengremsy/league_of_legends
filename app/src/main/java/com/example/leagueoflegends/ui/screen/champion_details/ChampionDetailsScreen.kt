package com.example.leagueoflegends.ui.screen.champion_details

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.leagueoflegends.config.ImageConfig
import com.example.leagueoflegends.domain.model.ChampionModel
import com.example.leagueoflegends.ui.screen.champion_details.composable.ChampionDetailsHeader
import com.example.leagueoflegends.ui.screen.champion_details.composable.ChampionDetailsLore
import com.example.leagueoflegends.ui.screen.champion_details.composable.ChampionPassive
import com.example.leagueoflegends.ui.screen.champion_details.composable.ChampionSpell

@Composable
fun ChampionDetailsScreen(champion: ChampionModel) {
    Scaffold { innerPadding ->
        LazyColumn(contentPadding = innerPadding) {
            item {
                AsyncImage(
                    model = ImageConfig.imageSplashUrl + "${champion.name}_0.jpg",
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )

                ChampionDetailsHeader(
                    champion,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                )
                ChampionDetailsLore(
                    lore = champion.lore ?: "",
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp)
                )
                champion.passive?.let { passive ->
                    ChampionPassive(
                        passive = passive,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 10.dp)
                    )
                }

                champion.spells.forEach { spell ->
                    ChampionSpell(
                        spell = spell,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 10.dp)
                    )
                }
            }
        }
    }
}