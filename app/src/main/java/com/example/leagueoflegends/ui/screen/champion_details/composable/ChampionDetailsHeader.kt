package com.example.leagueoflegends.ui.screen.champion_details.composable

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.leagueoflegends.config.ImageConfig
import com.example.leagueoflegends.domain.model.ChampionModel
import com.example.leagueoflegends.ui.theme.LeagueOfLegendsTheme

@Composable
fun ChampionDetailsHeader(champion: ChampionModel, modifier: Modifier = Modifier) {
    ListItem(
        modifier = modifier,
        headlineContent = {
            Text(
                text = champion.name ?: "",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
        },
        supportingContent = {
            Text(text = champion.tags.firstOrNull() ?: "")
        },
        leadingContent = {
            AsyncImage(
                model = ImageConfig.imageSquareUrl + "${champion.name}.png",
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
        },
        trailingContent = {
            Text(
                text = champion.title ?: "",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium,
            )
        },
    )
}

