package kz.batana.homecreditloyalty.auth

import com.google.gson.annotations.SerializedName
import kz.batana.homecreditloyalty.entity.Customer

/**
 * Data class for Token
 */


data class LoginResponse(
        @SerializedName("code") val code: Int,
        @SerializedName("user") val user: Customer?
)