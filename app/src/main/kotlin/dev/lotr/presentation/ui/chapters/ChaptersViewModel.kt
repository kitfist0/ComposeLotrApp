package dev.lotr.presentation.ui.chapters

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.lotr.domain.common.Result
import dev.lotr.domain.model.Chapter
import dev.lotr.domain.usecase.GetBookChaptersUseCase
import dev.lotr.presentation.navigation.NavParams
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChaptersViewModel @Inject constructor(
    private val getBookChaptersUseCase: GetBookChaptersUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _chapters = MutableStateFlow<List<Chapter>>(emptyList())
    val chapters: StateFlow<List<Chapter>> = _chapters.asStateFlow()

    init {
        val bookId = savedStateHandle.get<String>(NavParams.BOOK_ID).orEmpty()
        viewModelScope.launch {
            when (val result = getBookChaptersUseCase.invoke(bookId)) {
                is Result.Error -> Log.d(TAG, "Error: ${result.message}")
                is Result.Success -> _chapters.value = result.data
            }
        }
    }

    fun onChapterClicked(chapterId: String) {
        Log.d(TAG, "Chapter id: $chapterId")
    }

    companion object {
        private const val TAG = "CHAPTERS_VIEW_MODEL"
    }
}
