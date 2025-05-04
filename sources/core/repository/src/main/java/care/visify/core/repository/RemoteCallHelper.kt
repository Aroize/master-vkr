package care.visify.core.repository

import care.visify.api.models.error.ErrorResponse
import care.visify.api.models.error.InternalNetworkException
import care.visify.api.models.error.ResultWrapper
import care.visify.core.repository.sources.RemoteSource
import com.google.gson.Gson
import com.google.gson.stream.JsonReader
import retrofit2.HttpException
import java.io.IOException
import java.io.StringReader
import java.nio.charset.StandardCharsets

class RemoteCallHelper<T, Params>(
    private val delegate: RemoteSource<T, Params>
) : RemoteSource<T, Params> where Params : Parameters {

    private val gson = Gson()

    override suspend fun load(parameters: Params): T = safeCall { delegate.load(parameters) }

    private suspend fun<T> safeCall(call: suspend () -> T): T {
        val result = runCatching { call() }
            .fold(
                onSuccess = { ResultWrapper.Success(it) },
                onFailure = { wrapError(it) }
            )
        return when (result) {
            is ResultWrapper.Success -> result.value
            is ResultWrapper.Failure -> throw InternalNetworkException(result)
        }
    }

    private fun wrapError(throwable: Throwable): ResultWrapper<Nothing> {
        return when (throwable) {
            is IOException -> ResultWrapper.NetworkError(throwable)
            is HttpException -> {
                val response = throwable.response()?.errorBody()?.source()?.let {
                    val bodyString = it.readString(StandardCharsets.UTF_8)
                    gson.fromJson<ErrorResponse>(
                        JsonReader(StringReader(bodyString)),
                        ErrorResponse::class.java
                    )
                }
                ResultWrapper.Failure(throwable, throwable.code(), response)
            }
            else -> ResultWrapper.Failure(throwable, null, null)
        }
    }
}