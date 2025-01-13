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
import com.example.leagueoflegends.ui.screen.champion_list.ChampionListScreen
import com.example.leagueoflegends.ui.screen.champion_list.ChampionListViewModel
import com.example.leagueoflegends.ui.theme.LeagueOfLegendsTheme
import dagger.hilt.android.AndroidEntryPoint
import java.time.format.TextStyle

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
//            val viewModel = hiltViewModel<ChampionListViewModel>()

//            LeagueOfLegendsTheme {
//                Surface (modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background){
//                    Greeting("Android")
//
//                }
                val viewModel = hiltViewModel<ChampionListViewModel>()

                val state by viewModel.state.collectAsStateWithLifecycle()
                ChampionListScreen(state = state, onValueChange = viewModel::onSearchTextChange)

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