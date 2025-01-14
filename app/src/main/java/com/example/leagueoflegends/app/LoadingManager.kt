package com.example.leagueoflegends.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.leagueoflegends.MainScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

interface LoadingManager {
    companion object {
        val currentInstance = LoadingManagerImpl
    }
    @Composable
    fun Builder(content:@Composable (() -> Unit))
    fun show()
    fun hide()

}

object LoadingManagerImpl : LoadingManager {
    private var _isShowing = mutableStateOf(false)
    val isShowing: State<Boolean>
        get() = _isShowing

    @Composable
    override fun Builder(content: @Composable () -> Unit) {
        Box(modifier = Modifier.fillMaxSize()) {
            content.invoke()
            LoadingOverlay()

        }
    }

    override fun show() {
        CoroutineScope(Dispatchers.Main).launch {
            _isShowing.value= true
        }
    }

    override fun hide() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(200)
            _isShowing.value= false
        }
    }
    @Composable
    private fun LoadingOverlay() {
        if (_isShowing.value) { // Observes isShowing
            Dialog(
                onDismissRequest = {}
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color.White)
                }
            }
        }
    }

}