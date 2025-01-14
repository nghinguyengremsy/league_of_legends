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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

interface OverlayManager {

    companion object {
        val I = OverlayManagerImpl
    }

    @Composable
    fun Builder(content: @Composable (() -> Unit))

    fun show(
        entry: OverlayEntryData,
        dismissible: Boolean = false
    ): OverlayEntryControl

    fun hide(id: String)

    fun setLoadingZIndex(zindex: Int);
    fun showLoading(content: @Composable (() -> Unit)? = null): OverlayEntryControl
}


@OptIn(ExperimentalUuidApi::class)
object OverlayManagerImpl : OverlayManager {

    val overlay: Overlay = Overlay()

    private var loadingZIndex: Int = 99
    private val loadingId = "LOADING_${Uuid.random().toString()}"

    private val hashRequester =
        mutableMapOf<String, MutableList<String>>() // Entry's ID - Requester ID

    @Composable
    override fun Builder(content: @Composable () -> Unit) {
        Box(modifier = Modifier.fillMaxSize()) {
            content.invoke()
            OverlayEntries()

        }
    }

    override fun show(
        entry: OverlayEntryData,
        dismissible: Boolean
    ): OverlayEntryControl {
        val currentData = overlay.getOverlayEntryData(entry.id)
        val requesterId = Uuid.random().toString()
        addOverlayRequester(entry.id, requesterId)
        val control = OverlayEntryControl {
            removeOverlayRequester(entry.id, requesterId)
        }
        if (currentData == null) {
            overlay.insert(entry)
        }
        return control
    }

    override fun hide(id: String) {
        hashRequester[id]?.clear()
        overlay.remove(id)
    }

    override fun setLoadingZIndex(zindex: Int) {
        loadingZIndex = zindex
    }

    override fun showLoading(content: @Composable() (() -> Unit)?): OverlayEntryControl = show(
        entry = OverlayEntryData(
            id = loadingId, zindex = loadingZIndex,
            entry = OverlayEntry(
                content =
                {
                    if (content != null) {
                        content.invoke()
                    } else {
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
                },
            ),
        ),
    )

    @Composable
    private fun OverlayEntries() {
        overlay.currentState.value.entries.forEach { it ->
            it.content.invoke()
        }

    }

    private fun addOverlayRequester(overlayId: String, requesterId: String) {
        val requester = hashRequester[overlayId];
        if (requester.isNullOrEmpty()) {
            hashRequester[overlayId] = mutableListOf();
            hashRequester[overlayId]?.add(requesterId);
        } else {
            hashRequester[overlayId]?.add(requesterId);
        }
    }

    private fun removeOverlayRequester(overlayId: String, requesterId: String) {
        CoroutineScope(Dispatchers.Main).launch {
            delay(300)
            val requester = hashRequester[overlayId];
            requester?.remove(requesterId);
            if (requester.isNullOrEmpty()) {
                hide(overlayId);
            }
        }

    }
}


// Overlay
class Overlay {
    private val hashEntries = mutableMapOf<String, OverlayEntryData>()
    private val entries = mutableListOf<OverlayEntryData>()

    private var _currentState = mutableStateOf(OverlayState(emptyList()))
    val currentState: State<OverlayState>
        get() = _currentState

    fun hasEntry(id: String): Boolean = hashEntries[id] != null

    fun getOverlayEntryData(id: String?): OverlayEntryData? = hashEntries[id]

    fun insert(data: OverlayEntryData) {

        hashEntries[data.id] = data
        // if the entries is empty, just add new entry to list
        if (entries.isEmpty()) {
            entries.add(data)
        } else {
            // If there's an entry below the new entry, put it behind.
            val belowIndex = entries.indexOfLast { data.zindex > it.zindex }
            if (belowIndex != -1) {
                entries.add(belowIndex + 1, data)
            } else {
                entries.add(0, data)
            }
        }
        updateState()
    }

    fun remove(id: String) {
        val entry = hashEntries[id]
        if (entry != null) {
            hashEntries.remove(id)
            entries.remove(entry)
        }
        updateState()
    }

    private fun updateState() {
        _currentState.value = OverlayState(entries.map { it -> it.entry }.toList())
    }
}
data class OverlayState(val entries: List<OverlayEntry>)

// Overlay entry

class OverlayEntry(val content: @Composable () -> Unit)

data class OverlayEntryData(val id: String, val zindex: Int, val entry: OverlayEntry)

class OverlayEntryControl(private val dismissFnc: () -> Unit) {

    fun dismiss() {
        dismissFnc.invoke()
    }

}
