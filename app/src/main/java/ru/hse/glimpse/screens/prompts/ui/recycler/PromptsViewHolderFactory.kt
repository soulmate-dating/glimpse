package ru.hse.glimpse.screens.prompts.ui.recycler

import android.view.View
import ru.hse.glimpse.R
import ru.hse.glimpse.screens.common.recycler.items.ImagePromptViewHolder
import ru.hse.glimpse.screens.common.recycler.items.ProfileFeaturesViewHolder
import ru.hse.glimpse.screens.common.recycler.items.TextPromptViewHolder
import ru.hse.glimpse.screens.prompts.ui.recycler.data.EmptyPromptsTextViewHolder
import ru.tinkoff.mobile.tech.ti_recycler.base.BaseViewHolder
import ru.tinkoff.mobile.tech.ti_recycler_coroutines.base.CoroutinesHolderFactory

class PromptsViewHolderFactory : CoroutinesHolderFactory() {
    override fun createViewHolder(view: View, viewType: Int): BaseViewHolder<*>? {
        return when (viewType) {
            R.layout.item_prompts_empty_text -> EmptyPromptsTextViewHolder(view, clicks)
            R.layout.item_large_text_prompt -> TextPromptViewHolder(view, clicks)
            R.layout.item_large_image_prompt -> ImagePromptViewHolder(view, clicks)
            R.layout.item_profile_features -> ProfileFeaturesViewHolder(view)
            else -> null
        }
    }
}
