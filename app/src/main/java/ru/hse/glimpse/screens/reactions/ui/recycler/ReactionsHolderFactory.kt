package ru.hse.glimpse.screens.reactions.ui.recycler

import android.view.View
import ru.hse.glimpse.R
import ru.hse.glimpse.network.api.reactions.model.Reaction
import ru.hse.glimpse.screens.common.recycler.items.EmptyStateViewHolder
import ru.tinkoff.mobile.tech.ti_recycler.base.BaseViewHolder
import ru.tinkoff.mobile.tech.ti_recycler_coroutines.base.CoroutinesHolderFactory

class ReactionsHolderFactory(
    private val onSkip: (Reaction) -> Unit,
    private val onReply: (Reaction) -> Unit,
) : CoroutinesHolderFactory() {
    override fun createViewHolder(view: View, viewType: Int): BaseViewHolder<*>? {
        return when (viewType) {
            R.layout.item_image_reaction -> ImageReactionViewHolder(view, clicks, onSkip, onReply)
            R.layout.item_empty_state -> EmptyStateViewHolder(view)
            else -> null
        }
    }
}
