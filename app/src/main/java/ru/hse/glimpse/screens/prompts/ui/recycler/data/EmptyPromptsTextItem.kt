package ru.hse.glimpse.screens.prompts.ui.recycler.data

import android.graphics.Color
import android.view.View
import ru.hse.glimpse.R
import ru.hse.glimpse.databinding.ItemPromptsEmptyTextBinding
import ru.hse.glimpse.utils.views.makeTextLink
import ru.tinkoff.mobile.tech.ti_recycler.base.BaseViewHolder
import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped
import ru.tinkoff.mobile.tech.ti_recycler.clicks.TiRecyclerClickListener

data class EmptyPromptsTextItem(
    override val uid: String = "empty header prompts",
    override val viewType: Int = R.layout.item_prompts_empty_text,
) : ViewTyped

class EmptyPromptsTextViewHolder(
    view: View,
    clicks: TiRecyclerClickListener,
) : BaseViewHolder<EmptyPromptsTextItem>(view, clicks) {

    private val binding = ItemPromptsEmptyTextBinding.bind(view)

    override fun bind(item: EmptyPromptsTextItem) {
        makeTextLink(
            textView = binding.title,
            string = "2 required",
            color = Color.RED,
        )
        makeTextLink(
            textView = binding.title,
            string = "image",
            color = Color.BLUE,
        )
        makeTextLink(
            textView = binding.title,
            string = "text",
            color = Color.BLUE,
        )
    }
}
