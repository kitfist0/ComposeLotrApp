package dev.lotr.app.ui.chapters

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.lotr.app.navigation.NavParams
import dev.lotr.app.ui.base.BaseViewModel
import dev.lotr.domain.common.Result
import dev.lotr.domain.model.Chapter
import dev.lotr.domain.usecase.GetBookChaptersUseCase
import javax.inject.Inject

@HiltViewModel
class ChaptersViewModel @Inject constructor(
    private val getBookChaptersUseCase: GetBookChaptersUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<List<Chapter>>() {

    private val bookId = savedStateHandle.get<String>(NavParams.BOOK_ID).orEmpty()

    init {
        fetchData()
    }

    override suspend fun invokeUseCase(): Result<List<Chapter>> {
        return getBookChaptersUseCase.invoke(bookId)
    }

    fun onChapterClicked(chapterId: String) {
        Log.d(TAG, "Chapter id: $chapterId")
    }

    companion object {
        private const val TAG = "CHAPTERS_VIEW_MODEL"
    }
}
