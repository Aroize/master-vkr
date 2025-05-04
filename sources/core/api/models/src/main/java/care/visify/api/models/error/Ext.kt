package care.visify.api.models.error

fun wrapWithErrorCode(code: ClientStatusCodes.RemoteError): Throwable {
    return InternalNetworkException(
        resultWrapper = ResultWrapper.Failure(
            error = ErrorResponse(
                errorCode = code.code,
                message = code.name
            )
        )
    )
}