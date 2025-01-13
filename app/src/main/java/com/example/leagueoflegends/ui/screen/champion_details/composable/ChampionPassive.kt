package com.example.leagueoflegends.ui.screen.champion_details.composable

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.leagueoflegends.config.ImageConfig
import com.example.leagueoflegends.domain.model.PassiveModel
import com.example.leagueoflegends.domain.model.SpellModel

@Composable
fun ChampionPassive(passive: PassiveModel, modifier: Modifier = Modifier){
    ListItem(
        modifier = modifier,
        headlineContent = {
            Text(
                text = passive.name ?: "",

                )
        },
        supportingContent = {
            Text(text = passive.description ?: "")
        },
        leadingContent = {
            AsyncImage(
                model = ImageConfig.imagePassiveUrl + "${passive.image?.full}",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(32.dp).clip(RoundedCornerShape(8.dp))
            )
        },

        )
}