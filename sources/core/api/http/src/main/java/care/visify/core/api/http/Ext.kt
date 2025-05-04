package care.visify.core.api.http

import care.visify.api.models.error.ClientStatusCodes
import care.visify.api.models.error.InternalNetworkException
import okhttp3.Response
import retrofit2.HttpException
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun String.toBearer() = "Bearer $this"

fun List<Int>.toQueryParam() = this.joinToString(separator = ",")

//in format 'YYYY-MM-DD'
fun LocalDate.toQueryParam(): String = this.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

internal val Response.retryCount: Int
    get() {
        var currentResponse = priorResponse
        var result = 0
        while (currentResponse != null) {
            result++
            currentResponse = currentResponse.priorResponse
        }
        return result
    }

val Throwable?.isAuthException: Boolean
    get() {
        return when (this) {
            is HttpException -> code() == ClientStatusCodes.Failure.UNAUTHORIZED
            is InternalNetworkException -> (cause as? HttpException)
                ?.code() == ClientStatusCodes.Failure.UNAUTHORIZED

            else -> false
        }
    }