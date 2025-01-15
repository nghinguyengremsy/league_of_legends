package com.example.leagueoflegends.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

object Logger {
    private const val LOGGER_ID = "LOGGER"
    fun show() {
        OverlayManager.I.show(
            entry = OverlayEntryData(
                id = LOGGER_ID,
                zindex = 100,
                entry = OverlayEntry {
                    Box (modifier = Modifier.fillMaxSize()){
                        IconButton(
                            onClick = {
                            },
                            modifier = Modifier.align(Alignment.BottomEnd).padding(bottom = 70.dp, end = 20.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = null,
                                tint = Color.Black

                            )
                        }
                    }
                },
            ),
        )
    }
    fun hide() {
        OverlayManager.I.hide(LOGGER_ID)
    }
}