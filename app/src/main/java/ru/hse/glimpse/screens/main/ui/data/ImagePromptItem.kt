package ru.hse.glimpse.screens.main.ui.data

import android.view.View
import coil.load
import ru.hse.glimpse.R
import ru.hse.glimpse.databinding.ItemLargeImagePromptBinding
import ru.tinkoff.mobile.tech.ti_recycler.base.BaseViewHolder
import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped
import ru.tinkoff.mobile.tech.ti_recycler.clicks.TiRecyclerClickListener

data class ImagePromptItem(
    val content: String,
    val imageLink: String?,
): ViewTyped {

    override val uid: String
        get() = content + imageLink

    override val viewType: Int
        get() = R.layout.item_large_image_prompt
}

class ImagePromptViewHolder(
    view: View,
    clicks: TiRecyclerClickListener
) : BaseViewHolder<ImagePromptItem>(view, clicks) {

    private val binding = ItemLargeImagePromptBinding.bind(view)

    override fun bind(item: ImagePromptItem) {
        binding.content.text = item.content
        binding.image.load(item.imageLink) {
            crossfade(true)
        }

        with(binding.card) {
            isClickable = false
            isFocusable = false
        }
    }
}
