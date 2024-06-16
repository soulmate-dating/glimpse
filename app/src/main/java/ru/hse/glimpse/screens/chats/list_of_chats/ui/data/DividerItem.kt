package ru.hse.glimpse.screens.chats.list_of_chats.ui.data

import android.view.View
import ru.hse.glimpse.R
import ru.tinkoff.mobile.tech.ti_recycler.base.BaseViewHolder
import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped

class DividerItem : ViewTyped {
    override val uid: String
        get() = super.uid
    override val viewType: Int
        get() = R.layout.item_divider_chats
}

class DividerViewHolder(view: View) : BaseViewHolder<DividerItem>(view)
