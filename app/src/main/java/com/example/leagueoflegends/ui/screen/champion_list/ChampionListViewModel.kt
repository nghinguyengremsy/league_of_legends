package com.example.leagueoflegends.ui.screen.champion_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leagueoflegends.app.OverlayManager
import com.example.leagueoflegends.domain.model.toChampionList
import com.example.leagueoflegends.domain.repository.ChampionRepository
import com.skydoves.sandwich.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChampionListViewModel @Inject constructor(private val repo: ChampionRepository) :
    ViewModel() {
    private val _state = MutableStateFlow(ChampionListState())
    val state
        get() = _state.asStateFlow()

    init {

        viewModelScope.launch {
            val loader= OverlayManager.I.showLoading()
            repo.getAllChampions().onSuccess {
                _state.update {
                    val champions = data.champion.toChampionList()
                    it.copy(champions = champions, filteredChampions = champions)
                }
            }
            loader.dismiss()

        }
    }

    fun onSearchTextChange(text: String) {
        _state.update {
            it.copy(searchText = text, filteredChampions = it.champions.filter { champion ->
                champion.name?.contains(text, ignoreCase = true) == true
            })
        }
    }
}