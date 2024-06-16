package ru.hse.glimpse.screens.chats.dialog

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import coil.transform.CircleCropTransformation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import ru.hse.glimpse.R
import ru.hse.glimpse.databinding.FragmentDialogBinding
import ru.hse.glimpse.navigation.Screens
import ru.hse.glimpse.screens.chats.dialog.DialogFragment.Companion.DialogFragmentArgs
import ru.hse.glimpse.screens.chats.dialog.di.DialogComponent
import ru.hse.glimpse.screens.chats.dialog.presentation.DialogNews
import ru.hse.glimpse.screens.chats.dialog.presentation.DialogUiEvent
import ru.hse.glimpse.screens.chats.dialog.ui.mapper.DialogUiState
import ru.hse.glimpse.screens.chats.dialog.ui.recycler.DialogViewHolderFactory
import ru.hse.glimpse.utils.views.FlowFragment
import ru.hse.glimpse.utils.views.IArgs
import ru.hse.glimpse.utils.views.IFragmentArgs
import ru.hse.glimpse.utils.views.args
import ru.hse.glimpse.utils.views.setArgs
import ru.hse.glimpse.utils.views.showAlert
import ru.tinkoff.kotea.android.lifecycle.collectOnCreate
import ru.tinkoff.kotea.android.storeViaViewModel
import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped
import ru.tinkoff.mobile.tech.ti_recycler_coroutines.TiRecyclerCoroutines

@AndroidEntryPoint
class DialogFragment : FlowFragment<DialogComponent>(R.layout.fragment_dialog),
    IFragmentArgs<DialogFragmentArgs> {

    companion object {

        fun newInstance(
            companionId: String,
            companionName: String,
            avatarLink: String,
        ): DialogFragment {
            return DialogFragment().setArgs(
                DialogFragmentArgs(
                    companionId,
                    companionName,
                    avatarLink
                )
            )
        }

        @Parcelize
        data class DialogFragmentArgs(
            val companionId: String,
            val companionName: String,
            val avatarLink: String,
        ) : IArgs
    }

    private val args by args()

    private val binding by viewBinding(FragmentDialogBinding::bind)
    private val store by storeViaViewModel { component.createDialogStore() }

    private lateinit var recycler: TiRecyclerCoroutines<ViewTyped>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        store.collectOnCreate(
            fragment = this,
            uiStateMapper = component.dialogUiStateMapper,
            stateCollector = ::render,
            newsCollector = ::news,
        )
        store.dispatch(DialogUiEvent.OnInit(companionId = args.companionId))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initAppBar()
        initRecycler()
        initViews()
        subscribeOnMessages()
    }

    private fun initAppBar() {
        binding.name.text = args.companionName
        binding.avatar.load(args.avatarLink) {
            crossfade(true)
            placeholder(R.drawable.icon_account_circle)
            transformations(CircleCropTransformation())
        }
        binding.backArrow.setOnClickListener { store.dispatch(DialogUiEvent.BackClicked) }
    }

    private fun initRecycler() {
        val layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            true,
        )
        binding.recycler.layoutManager = layoutManager
        recycler = TiRecyclerCoroutines(
            binding.recycler,
            DialogViewHolderFactory(),
        )
    }

    private fun initViews() {
        binding.sendButton.setOnClickListener {
            if (binding.messageText.editText?.text.isNullOrEmpty()) {
                binding.messageText.editText?.error = ""
            } else {
                store.dispatch(
                    DialogUiEvent.SendMessage(binding.messageText.editText!!.text.toString())
                )
                binding.messageText.editText?.setText("")
            }
        }
    }

    private fun subscribeOnMessages() {
        viewLifecycleOwner.lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                // this block is automatically executed when moving into
                // the started state, and cancelled when stopping.
                while (true) {
                    store.dispatch(DialogUiEvent.GetMessages(args.companionId))
                    delay(3000)
                }
            }
        }
    }

    private fun render(uiState: DialogUiState) {
        recycler.setItems(uiState.items)
    }

    private fun news(news: DialogNews) {
        when (news) {
            is DialogNews.NavigateBack -> router.backTo(Screens.ChatsScreen())
            is DialogNews.ShowError -> showAlert(news.message)
        }
    }
}
