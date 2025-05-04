package care.visify.api.models.error

class InternalNetworkException(
    val resultWrapper: ResultWrapper.Failure
) : RuntimeException(resultWrapper.toString(), resultWrapper.exception) {
    val isNetworkError: Boolean
        get() = resultWrapper is ResultWrapper.NetworkError

    val visifyErrorCode: Int
        get() = resultWrapper.error?.errorCode ?: ClientStatusCodes.RemoteError.UNKNOWN.code
}