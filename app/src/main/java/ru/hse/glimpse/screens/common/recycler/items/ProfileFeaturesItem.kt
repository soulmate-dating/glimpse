package ru.hse.glimpse.screens.common.recycler.items

import android.view.View
import ru.hse.glimpse.R
import ru.hse.glimpse.databinding.ItemProfileFeaturesBinding
import ru.tinkoff.mobile.tech.ti_recycler.base.BaseViewHolder
import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped

data class ProfileFeaturesItem(
    val age: String,
    val height: String,
    val intention: String,
    val drinks: String,
    val smokes: String,
    val familyPlans: String,
    val lookingFor: String,
) : ViewTyped {

    override val uid: String
        get() = age + height + intention
    override val viewType: Int
        get() = R.layout.item_profile_features
}

class ProfileFeaturesViewHolder(
    view: View,
) : BaseViewHolder<ProfileFeaturesItem>(view) {

    private val binding = ItemProfileFeaturesBinding.bind(view)

    override fun bind(item: ProfileFeaturesItem) {
        binding.age.text = item.age
        binding.height.text = item.height
        binding.relationship.text = item.intention
        binding.drink.text = item.drinks
        binding.smoke.text = item.smokes
        binding.family.text = item.familyPlans
        binding.lookingFor.text = item.lookingFor
    }
}
