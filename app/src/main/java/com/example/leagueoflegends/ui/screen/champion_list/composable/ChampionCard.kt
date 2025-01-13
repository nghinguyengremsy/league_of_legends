package com.example.leagueoflegends.ui.screen.champion_list.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.leagueoflegends.config.ImageConfig
import com.example.leagueoflegends.domain.model.ChampionModel
import com.example.leagueoflegends.ui.theme.LeagueOfLegendsTheme

@Composable
fun ChampionCard(
    champion: ChampionModel,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        AsyncImage(
            model = ImageConfig.imageLoadingUrl + "${champion.name}_0.jpg",
            contentScale = ContentScale.FillWidth,
            contentDescription = null,
            modifier = Modifier
                .clip(
                    RoundedCornerShape(20.dp)
                )
                .weight(0.3f)
                .height(180.dp)
        )
        Column(
            modifier = Modifier
                .weight(0.7f)
                .padding(vertical = 10.dp)
        ) {
            Text(
                text = champion.name ?: "",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = champion.blurb ?: "", overflow = TextOverflow.Ellipsis,
                maxLines = 3,
                style = MaterialTheme.typography.bodyMedium,
                lineHeight = 24.sp
            )
        }
    }
}

@Preview
@Composable
private fun ChampionCardPreview() {
    LeagueOfLegendsTheme {
//        ChampionCard()
    }
}
