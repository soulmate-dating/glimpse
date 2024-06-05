package ru.hse.glimpse.screens.main.ui.recycler

import android.view.View
import ru.hse.glimpse.R
import ru.hse.glimpse.network.api.prompts.model.Prompt
import ru.hse.glimpse.screens.common.recycler.items.ImagePromptViewHolder
import ru.hse.glimpse.screens.common.recycler.items.ProfileFeaturesViewHolder
import ru.hse.glimpse.screens.common.recycler.items.TextPromptViewHolder
import ru.tinkoff.mobile.tech.ti_recycler.base.BaseViewHolder
import ru.tinkoff.mobile.tech.ti_recycler_coroutines.base.CoroutinesHolderFactory

class MainViewHolderFactory(
    private val onPromptClicked: (Prompt) -> Unit,
) : CoroutinesHolderFactory() {

    override fun createViewHolder(view: View, viewType: Int): BaseViewHolder<*>? {
        return when (viewType) {
            R.layout.item_large_image_prompt -> ImagePromptViewHolder(view, clicks, onPromptClicked)
            R.layout.item_large_text_prompt -> TextPromptViewHolder(view, clicks, onPromptClicked)
            R.layout.item_profile_features -> ProfileFeaturesViewHolder(view)
            else -> null
        }
    }
}
