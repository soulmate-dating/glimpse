package ru.hse.glimpse.network.common.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class AuthNetworkResponse(
    val data: AuthNetworkResponseData,
    val error: String?,
) {

    @Keep
    data class AuthNetworkResponseData(

        val id: String,

        @SerializedName("access_token")
        val accessToken: String,

        @SerializedName("refresh_token")
        val refreshToken: String,
    )
}
