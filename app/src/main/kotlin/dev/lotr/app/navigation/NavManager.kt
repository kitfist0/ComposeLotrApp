package dev.lotr.app.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class NavManager {

    private val _routes = MutableSharedFlow<String>(extraBufferCapacity = 1)
    val routes = _routes.asSharedFlow()

    fun navigate(route: String) {
        _routes.tryEmit(route)
    }
}
