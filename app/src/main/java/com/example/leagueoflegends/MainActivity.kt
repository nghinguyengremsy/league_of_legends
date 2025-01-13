package com.example.leagueoflegends

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.pm.ShortcutInfoCompat.Surface
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
import dagger.hilt.android.AndroidEntryPoint
import java.time.format.TextStyle

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            val viewModel = hiltViewModel<ChampionListViewModel>()
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
                            navigate = {name -> navController.navigate(ChampionDetails(name))}
                        )
                    }
                    composable<ChampionDetails> {
                        val viewModel = hiltViewModel<ChampionDetailsViewModel>()
                        viewModel.champion.value?.let {
                            ChampionDetailsScreen(it)
                        }
                    }
                }


            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LeagueOfLegendsTheme {
        Greeting("Android")
    }
}