package ru.hse.glimpse.screens.prompts.ui.recycler.data

import android.view.View
import androidx.annotation.StringRes
import ru.hse.glimpse.R
import ru.hse.glimpse.databinding.ItemHeaderBinding
import ru.tinkoff.mobile.tech.ti_recycler.base.BaseViewHolder
import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped
import ru.tinkoff.mobile.tech.ti_recycler.clicks.TiRecyclerClickListener

data class HeaderItem(
    @StringRes val title: Int,
) : ViewTyped {

    override val uid: String
        get() = "prompts header $title"

    override val viewType: Int
        get() = R.layout.item_header
}

class HeaderViewHolder(
    view: View,
    clicks: TiRecyclerClickListener
) : BaseViewHolder<HeaderItem>(view, clicks) {

    private val binding = ItemHeaderBinding.bind(view)

    override fun bind(item: HeaderItem) {
        binding.title.setText(item.title)
    }
}
