package dev.lotr.domain.usecase

import dev.lotr.domain.common.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * Executes business logic using Coroutines
 */
abstract class BaseUseCase <in P, R>(private val coroutineDispatcher: CoroutineDispatcher) {

    /** Executes the use case asynchronously and returns a [Result]
     *
     * @return a [Result]
     *
     * @param parameters the input parameters to run the use case with
     */
    suspend operator fun invoke(parameters: P): Result<R> {
        return try {
            withContext(coroutineDispatcher) {
                execute(parameters).let {
                    Result.Success(it)
                }
            }
        } catch (e: Exception) {
            Result.Error(e.message.orEmpty())
        }
    }

    /**
     * Override this to set the code to be executed
     */
    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(parameters: P): R
}
