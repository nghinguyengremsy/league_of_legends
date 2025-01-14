package com.example.leagueoflegends.ui.screen.champion_details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leagueoflegends.domain.model.ChampionModel
import com.example.leagueoflegends.domain.repository.ChampionRepository
import com.example.leagueoflegends.app.ChampionDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.navigation.toRoute
import com.example.leagueoflegends.app.OverlayManager
import com.skydoves.sandwich.onSuccess
import kotlinx.coroutines.delay

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
           val loader= OverlayManager.I.showLoading()
            repo.getChampionByName(args.name).onSuccess {
                champion.value = data.champion.values.firstOrNull()
            }
            loader.dismiss()
        }
    }
}