package ru.hse.glimpse.screens.common.recycler.items

import android.view.View
import androidx.annotation.DrawableRes
import ru.hse.glimpse.R
import ru.hse.glimpse.databinding.ItemEmptyStateBinding
import ru.tinkoff.mobile.tech.ti_recycler.base.BaseViewHolder
import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped

data class EmptyStateItem(
    val mainMessage: String,
    @DrawableRes val imageRes: Int,
) : ViewTyped {
    override val uid: String
        get() = mainMessage
    override val viewType: Int
        get() = R.layout.item_empty_state
}

class EmptyStateViewHolder(view: View) : BaseViewHolder<EmptyStateItem>(view) {

    private val binding = ItemEmptyStateBinding.bind(view)

    override fun bind(item: EmptyStateItem) {
        binding.mainText.text = item.mainMessage
        binding.image.setImageResource(item.imageRes)
    }
}
