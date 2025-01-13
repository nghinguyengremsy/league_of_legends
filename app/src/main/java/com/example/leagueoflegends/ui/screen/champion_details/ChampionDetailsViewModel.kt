package com.example.leagueoflegends.ui.screen.champion_details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leagueoflegends.domain.model.ChampionModel
import com.example.leagueoflegends.domain.repository.ChampionRepository
import com.example.leagueoflegends.ui.util.ChampionDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.navigation.toRoute
import com.skydoves.sandwich.onSuccess

@HiltViewModel
class ChampionDetailsViewModel @Inject constructor(
    private val repo: ChampionRepository,
    private val savedStateHandle: SavedStateHandle
) :
    ViewModel() {
    var champion = mutableStateOf<ChampionModel?>(null)

    init {
        val args = savedStateHandle.toRoute<ChampionDetails>()
        viewModelScope.launch {
            repo.getChampionByName(args.name).onSuccess {
                champion.value= data.champion.values.firstOrNull()
            }
        }
    }
}