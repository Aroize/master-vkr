package care.visify.api.models.error

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T): ResultWrapper<T>()
    open class Failure(
        val exception: Throwable? = null,
        val code: Int? = null,
        val error: ErrorResponse? = null
    ): ResultWrapper<Nothing>()
    class NetworkError(exception: Throwable): Failure(exception)
}