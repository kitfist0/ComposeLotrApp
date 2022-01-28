package dev.lotr.app.ui.books

import dev.lotr.domain.common.Result
import dev.lotr.domain.usecase.GetBooksUseCase
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*

@ExperimentalCoroutinesApi
class BooksViewModelTest : FreeSpec({

    "Feature: Books" - {
        // region Common
        lateinit var testDispatcher: TestDispatcher
        beforeAny {
            testDispatcher = UnconfinedTestDispatcher()
            Dispatchers.setMain(testDispatcher)
        }
        afterAny {
            Dispatchers.resetMain()
        }
        // endregion

        "Scenario: Fetch books" - {
            // region Variables
            lateinit var viewModel: BooksViewModel
            // endregion
            "When: Server returns books" {
                viewModel = getViewModelForCurrentScenario(
                    getBooksUseCase = mockk {
                        coEvery { this@mockk.invoke(null) } returns Result.Success(emptyList())
                    }
                )
            }
            "Then: Success state" {
                val isSuccess = viewModel.stateFlow.value is Result.Success
                isSuccess.shouldBeTrue()
            }
            "When: Server returns error" {
                viewModel = getViewModelForCurrentScenario(
                    getBooksUseCase = mockk {
                        coEvery { this@mockk.invoke(null) } returns Result.Error("")
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
    getBooksUseCase: GetBooksUseCase = mockk(),
): BooksViewModel {
    return BooksViewModel(getBooksUseCase, mockk())
}
