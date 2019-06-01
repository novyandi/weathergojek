package com.android.weathergojek.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Novyandi N. on 01/06/2019.
 * novyandinurahmad@hotmail.com
 */
data class ForecastDayResponse(
    @SerializedName("date")
    val date: String,
    @SerializedName("date_epoch")
    val dateEpoch: Int,
    @SerializedName("day")
    val day: DayResponse
)
