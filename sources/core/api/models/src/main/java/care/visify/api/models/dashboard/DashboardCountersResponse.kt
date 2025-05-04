package care.visify.api.models.dashboard

import com.google.gson.annotations.SerializedName

//верхние каунтеры
data class DashboardCountersResponse(
    @SerializedName("visitsToday")
    val visitsToday: Int,
    @SerializedName("visitsOnWeek")
    val visitsOnWeek: Int?,
    @SerializedName("newChats")
    val newChats: Int?,
    @SerializedName("availableMarketOrders")
    val availableMarketOrders: Int?,
)