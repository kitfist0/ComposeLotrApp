package dev.lotr.app.ui

import io.kotest.core.spec.style.scopes.FreeSpecContainerScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

@ExperimentalCoroutinesApi
fun FreeSpecContainerScope.setUnconfinedDispatcherAsMainBeforeEachScenario() {

    beforeContainer {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    afterContainer {
        Dispatchers.resetMain()
    }
}
