package com.example.leagueoflegends.ui.screen.champion_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.example.leagueoflegends.config.ImageConfig
import com.example.leagueoflegends.domain.model.ChampionModel
import com.example.leagueoflegends.ui.screen.champion_details.composable.ChampionDetailsHeader
import com.example.leagueoflegends.ui.screen.champion_details.composable.ChampionDetailsLore
import com.example.leagueoflegends.ui.screen.champion_details.composable.ChampionPassive
import com.example.leagueoflegends.ui.screen.champion_details.composable.ChampionSpell
import java.util.Stack

@Composable
fun ChampionDetailsScreen(champion: ChampionModel, navController: NavHostController) {
    Scaffold(
//        topBar = {
//            TopBar {
//                navController.navigateUp()
//            }
//        },
        content = { innerPadding ->
            LazyColumn(contentPadding = innerPadding) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    ) {
                        AsyncImage(
                            model = ImageConfig.imageSplashUrl + "${champion.name}_0.jpg",
                            contentDescription = null,
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier
                                .fillMaxSize()

                        )
                        IconButton(
                            onClick = { navController.navigateUp() },
                            modifier = Modifier.align(Alignment.TopStart)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }


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
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(navigateUp: () -> Unit) {
    TopAppBar(
        title = {
            Text("")
        },
        navigationIcon = {
            IconButton(onClick = navigateUp) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                )
            }
        })
}