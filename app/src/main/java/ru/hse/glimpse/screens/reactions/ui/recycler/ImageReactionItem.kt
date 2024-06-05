package ru.hse.glimpse.screens.reactions.ui.recycler

import android.view.View
import coil.load
import ru.hse.glimpse.R
import ru.hse.glimpse.databinding.ItemImageReactionBinding
import ru.hse.glimpse.network.api.reactions.model.Reaction
import ru.tinkoff.mobile.tech.ti_recycler.base.BaseViewHolder
import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped
import ru.tinkoff.mobile.tech.ti_recycler.clicks.TiRecyclerClickListener

data class ImageReactionItem(
    val comment: String,
    val avatarLink: String,
    val firstName: String,
    val reaction: Reaction,
) : ViewTyped {
    override val uid: String
        get() = comment + avatarLink + firstName
    override val viewType: Int
        get() = R.layout.item_image_reaction
}

class ImageReactionViewHolder(
    view: View,
    clicks: TiRecyclerClickListener,
    private val onSkip: (Reaction) -> Unit,
    private val onReply: (Reaction) -> Unit,
) : BaseViewHolder<ImageReactionItem>(view, clicks) {

    private val binding = ItemImageReactionBinding.bind(view)

    override fun bind(item: ImageReactionItem) {
        binding.image.load(item.avatarLink) {
            crossfade(true)
        }
        binding.firstName.text = item.firstName
        binding.comment.text = item.comment

        binding.dismiss.setOnClickListener { onSkip(item.reaction) }
        binding.sendMessage.setOnClickListener { onReply(item.reaction) }

        with(binding.card) {
            isClickable = false
            isFocusable = false
        }
    }
}
