package ru.hse.glimpse.network.api.profile.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Profile(

    @SerializedName("user_id")
    val userId: String,

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

    @SerializedName("profile_pic")
    val profilePictureUrl: String? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Profile) return false

        // Compares properties for structural equality
        return this.userId == other.userId
                && this.firstName == other.firstName
                && this.lastName == other.lastName
                && this.birthDate == other.birthDate
                && this.sex == other.sex
                && this.preference == other.preference
                && this.intention == other.intention
                && this.height == other.height
                && this.hasChildren == other.hasChildren
                && this.familyPlans == other.familyPlans
                && this.drinksAlcohol == other.drinksAlcohol
                && this.smokes == other.smokes
    }

    override fun hashCode(): Int {
        var result = userId.hashCode()
        result = 31 * result + firstName.hashCode()
        result = 31 * result + lastName.hashCode()
        result = 31 * result + birthDate.hashCode()
        result = 31 * result + sex.hashCode()
        result = 31 * result + preference.hashCode()
        result = 31 * result + intention.hashCode()
        result = 31 * result + height
        result = 31 * result + hasChildren.hashCode()
        result = 31 * result + familyPlans.hashCode()
        result = 31 * result + drinksAlcohol.hashCode()
        result = 31 * result + smokes.hashCode()
        result = 31 * result + location.hashCode()
        result = 31 * result + (profilePictureUrl?.hashCode() ?: 0)
        return result
    }
}
