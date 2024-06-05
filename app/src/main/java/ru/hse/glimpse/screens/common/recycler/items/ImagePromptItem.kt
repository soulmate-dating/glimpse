package ru.hse.glimpse.screens.common.recycler.items

import android.view.View
import androidx.core.view.isVisible
import coil.load
import ru.hse.glimpse.R
import ru.hse.glimpse.databinding.ItemLargeImagePromptBinding
import ru.hse.glimpse.network.api.prompts.model.Prompt
import ru.tinkoff.mobile.tech.ti_recycler.base.BaseViewHolder
import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped
import ru.tinkoff.mobile.tech.ti_recycler.clicks.TiRecyclerClickListener

data class ImagePromptItem(
    val question: String,
    val imageLink: String?,
    val isLikeButtonVisible: Boolean = true,
    val prompt: Prompt,
): ViewTyped {

    override val uid: String
        get() = question + imageLink

    override val viewType: Int
        get() = R.layout.item_large_image_prompt
}

class ImagePromptViewHolder(
    view: View,
    clicks: TiRecyclerClickListener,
    private val onPromptClicked: (Prompt) -> Unit = {},
) : BaseViewHolder<ImagePromptItem>(view, clicks) {

    private val binding = ItemLargeImagePromptBinding.bind(view)

    override fun bind(item: ImagePromptItem) {
        binding.content.text = item.question
        binding.image.load(item.imageLink) {
            crossfade(true)
        }

        binding.floatingActionButtonImage.isVisible = item.isLikeButtonVisible
        binding.floatingActionButtonImage.setOnClickListener { onPromptClicked(item.prompt) }

        with(binding.card) {
            isClickable = false
            isFocusable = false
        }
    }
}
