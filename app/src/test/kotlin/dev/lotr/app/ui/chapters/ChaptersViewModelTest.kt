package dev.lotr.app.ui.chapters

import androidx.lifecycle.SavedStateHandle
import dev.lotr.domain.common.Result
import dev.lotr.domain.usecase.GetBookChaptersUseCase
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*

@ExperimentalCoroutinesApi
class ChaptersViewModelTest : FreeSpec({

    "Feature: Chapters" - {
        // region Common
        lateinit var testDispatcher: TestDispatcher
        beforeContainer {
            testDispatcher = UnconfinedTestDispatcher()
            Dispatchers.setMain(testDispatcher)
        }
        afterContainer {
            Dispatchers.resetMain()
        }
        // endregion

        "Scenario: Fetch chapters" - {
            // region Variables
            lateinit var viewModel: ChaptersViewModel
            // endregion
            "When: Server returns chapters" {
                viewModel = getViewModelForCurrentScenario(
                    getBookChaptersUseCase = mockk {
                        coEvery { this@mockk.invoke(any()) } returns Result.Success(emptyList())
                    }
                )
            }
            "Then: Success state" {
                val isSuccess = viewModel.stateFlow.value is Result.Success
                isSuccess.shouldBeTrue()
            }
            "When: Server returns error" {
                viewModel = getViewModelForCurrentScenario(
                    getBookChaptersUseCase = mockk {
                        coEvery { this@mockk.invoke(any()) } returns Result.Error("")
                    }
                )
            }
            "Then: Error state" {
                val isError = viewModel.stateFlow.value is Result.Error
                isError.shouldBeTrue()
            }
        }
    }
})

private fun getViewModelForCurrentScenario(
    getBookChaptersUseCase: GetBookChaptersUseCase = mockk(),
): ChaptersViewModel {
    val savedStateHandle = mockk<SavedStateHandle> {
        every { get<String>(any()) } returns ""
    }
    return ChaptersViewModel(getBookChaptersUseCase, savedStateHandle)
}
