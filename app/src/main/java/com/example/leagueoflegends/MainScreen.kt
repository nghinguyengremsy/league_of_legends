package com.example.leagueoflegends

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.leagueoflegends.ui.screen.champion_details.ChampionDetailsScreen
import com.example.leagueoflegends.ui.screen.champion_details.ChampionDetailsViewModel
import com.example.leagueoflegends.ui.screen.champion_list.ChampionListScreen
import com.example.leagueoflegends.ui.screen.champion_list.ChampionListViewModel
import com.example.leagueoflegends.ui.theme.LeagueOfLegendsTheme
import com.example.leagueoflegends.ui.util.ChampionDetails
import com.example.leagueoflegends.ui.util.ChampionList

@Composable
fun MainScreen() {
    LeagueOfLegendsTheme {

        val navController = rememberNavController()
        NavHost(
            navController = navController, startDestination = ChampionList
        ) {
            composable<ChampionList> {
                val viewModel = hiltViewModel<ChampionListViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()
                ChampionListScreen(
                    state = state,
                    onValueChange = viewModel::onSearchTextChange,
                    navigate = { name -> navController.navigate(ChampionDetails(name)) }
                )
            }
            composable<ChampionDetails> {
                val viewModel = hiltViewModel<ChampionDetailsViewModel>()
                viewModel.champion.value?.let {
                    ChampionDetailsScreen(champion = it, navController = navController)
                }
            }
        }


    }
}