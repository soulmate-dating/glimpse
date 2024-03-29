package ru.hse.glimpse.screens.main.ui.recycler

import android.view.View
import ru.hse.glimpse.R
import ru.hse.glimpse.screens.main.ui.data.ImagePromptViewHolder
import ru.hse.glimpse.screens.main.ui.data.TextPromptViewHolder
import ru.tinkoff.mobile.tech.ti_recycler.base.BaseViewHolder
import ru.tinkoff.mobile.tech.ti_recycler_coroutines.base.CoroutinesHolderFactory

class MainViewHolderFactory : CoroutinesHolderFactory() {

    override fun createViewHolder(view: View, viewType: Int): BaseViewHolder<*>? {
        return when (viewType) {
            R.layout.item_large_image_prompt -> ImagePromptViewHolder(view, clicks)
            R.layout.item_large_text_prompt -> TextPromptViewHolder(view, clicks)
            else -> null
        }
    }
}
