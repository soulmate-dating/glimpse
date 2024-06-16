package ru.hse.glimpse.screens.chats.dialog.ui.data

import android.view.View
import ru.hse.glimpse.R
import ru.hse.glimpse.databinding.ItemChatDateDividerBinding
import ru.tinkoff.mobile.tech.ti_recycler.base.BaseViewHolder
import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped

data class DateDividerItem(
    val date: String,
) : ViewTyped {
    override val uid: String
        get() = date
    override val viewType: Int
        get() = R.layout.item_chat_date_divider
}

class DateDividerViewHolder(view: View) : BaseViewHolder<DateDividerItem>(view) {

    private val binding = ItemChatDateDividerBinding.bind(view)

    override fun bind(item: DateDividerItem) {
        binding.date.text = item.date
    }
}
