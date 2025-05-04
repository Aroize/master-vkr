package care.visify.core.api.sse.service

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Streaming

typealias SseCall = Call<ResponseBody>

interface ConnectionService {
    @Streaming
    @GET("/notifications/connect/v1")
    fun connect(): SseCall
}