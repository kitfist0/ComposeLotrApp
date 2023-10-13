package dev.lotr.app.navigation

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class NavManager {

    private var prevRoute: String? = null

    private val _routes = Channel<String>()
    val routes = _routes.receiveAsFlow()

    fun navigate(route: String) {
        if (prevRoute != route) {
            _routes.trySend(route)
            prevRoute = route
        }
    }
}
