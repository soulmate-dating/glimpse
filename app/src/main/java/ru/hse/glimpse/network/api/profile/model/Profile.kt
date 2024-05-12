package ru.hse.glimpse.network.api.profile.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Profile(

    @SerializedName("first_name")
    val firstName: String,

    @SerializedName("last_name")
    val lastName: String,

    @SerializedName("birth_date")
    val birthDate: String,

    @SerializedName("sex")
    val sex: String,

    @SerializedName("preferred_partner")
    val preference: String,

    @SerializedName("intention")
    val intention: String,

    @SerializedName("height")
    val height: Int,

    @SerializedName("has_children")
    val hasChildren: Boolean,

    @SerializedName("family_plans")
    val familyPlans: String,

    @SerializedName("drinks_alcohol")
    val drinksAlcohol: String,

    @SerializedName("smokes")
    val smokes: String,

    @SerializedName("location")
    val location: String = "location",
)
