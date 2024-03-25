package ru.hse.glimpse.screens.main.ui.data

import android.view.View
import androidx.core.view.isVisible
import ru.hse.glimpse.R
import ru.hse.glimpse.databinding.ItemLargeTextPromptBinding
import ru.tinkoff.mobile.tech.ti_recycler.base.BaseViewHolder
import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped
import ru.tinkoff.mobile.tech.ti_recycler.clicks.TiRecyclerClickListener

data class TextPromptItem(
    val type: String?,
    val content: String,
) : ViewTyped {

    override val uid: String
        get() = type + content
    override val viewType: Int
        get() = R.layout.item_large_text_prompt
}

class TextPromptViewHolder(
    view: View,
    clicks: TiRecyclerClickListener
) : BaseViewHolder<TextPromptItem>(view, clicks) {

    private val binding = ItemLargeTextPromptBinding.bind(view)

    override fun bind(item: TextPromptItem) {
        binding.type.isVisible = item.type != null
        item.type.let {
            binding.type.text = item.type
        }
        binding.content.text = item.content

        with(binding.card) {
            isClickable = false
            isFocusable = false
        }
    }
}
