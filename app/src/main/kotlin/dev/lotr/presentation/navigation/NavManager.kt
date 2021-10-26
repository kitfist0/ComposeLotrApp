package dev.lotr.presentation.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class NavManager {

    private val _routes = MutableSharedFlow<String>(extraBufferCapacity = 1)
    val routes = _routes.asSharedFlow()

    fun navigate(navDest: NavDest) {
        _routes.tryEmit(navDest.toString())
    }

    fun navigate(navDest: NavDest, argument: String) {
        _routes.tryEmit("$navDest/$argument")
    }
}
