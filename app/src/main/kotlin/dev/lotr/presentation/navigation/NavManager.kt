package dev.lotr.presentation.navigation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class NavManager {

    private var _routes = MutableStateFlow<String?>(null)
    val routes: StateFlow<String?> get() = _routes.asStateFlow()

    fun navigate(navDest: NavDest) {
        _routes.value = navDest.toString()
    }

    fun navigate(navDest: NavDest, argument: String) {
        _routes.value = "$navDest/$argument"
    }
}
