package ru.hse.glimpse.screens.reactions

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.hse.glimpse.R
import ru.hse.glimpse.databinding.FragmentReactionsBinding
import ru.hse.glimpse.navigation.Screens
import ru.hse.glimpse.screens.reactions.di.ReactionsComponent
import ru.hse.glimpse.screens.reactions.presentation.ReactionsNews
import ru.hse.glimpse.screens.reactions.presentation.ReactionsUiEvent
import ru.hse.glimpse.screens.reactions.ui.dialogs.ReplyOnReactionBottomSheetDialog
import ru.hse.glimpse.screens.reactions.ui.dialogs.ReplyOnReactionBottomSheetDialog.ReplyOnReactionBottomSheetDialogListener
import ru.hse.glimpse.screens.reactions.ui.mapper.ReactionsUiState
import ru.hse.glimpse.screens.reactions.ui.recycler.ReactionsHolderFactory
import ru.hse.glimpse.utils.views.FlowFragment
import ru.hse.glimpse.utils.views.showAlert
import ru.tinkoff.kotea.android.lifecycle.collectOnCreate
import ru.tinkoff.kotea.android.storeViaViewModel
import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped
import ru.tinkoff.mobile.tech.ti_recycler_coroutines.TiRecyclerCoroutines

@AndroidEntryPoint
class ReactionsFragment : FlowFragment<ReactionsComponent>(R.layout.fragment_reactions),
    ReplyOnReactionBottomSheetDialogListener {

    private val binding by viewBinding(FragmentReactionsBinding::bind)
    private val store by storeViaViewModel { component.createReactionsStore() }
    private lateinit var recycler: TiRecyclerCoroutines<ViewTyped>

    private lateinit var bottomSheet: ReplyOnReactionBottomSheetDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        store.collectOnCreate(
            fragment = this,
            uiStateMapper = component.createReactionsUiStateMapper,
            stateCollector = ::render,
            newsCollector = ::handleNews,
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBottomBar()
        initRecycler()
    }

    private fun initRecycler() {
        recycler = TiRecyclerCoroutines(
            binding.recycler,
            ReactionsHolderFactory(
                onSkip = { store.dispatch(ReactionsUiEvent.SkipReaction(it)) },
                onReply = { store.dispatch(ReactionsUiEvent.ReplyOnReaction(it)) },
            ),
        )
    }

    private fun initBottomBar() {
        binding.bottomNavigation.selectedItemId = R.id.liked
        binding.bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.main -> {
                    store.dispatch(ReactionsUiEvent.MainScreenClicked)
                    true
                }

                R.id.chats -> {
                    store.dispatch(ReactionsUiEvent.ChatsScreenClicked)
                    true
                }

                R.id.account -> {
                    store.dispatch(ReactionsUiEvent.AccountScreenClicked)
                    true
                }

                else -> false
            }
        }
    }

    private fun controlShimmersVisibility(isLoading: Boolean) {
        if (isLoading) {
            binding.shimmers.startShimmer()
        } else {
            binding.shimmers.apply {
                stopShimmer()
                visibility = View.GONE
            }
            binding.recycler.visibility = View.VISIBLE
        }
    }

    private fun render(uiState: ReactionsUiState) {
        controlShimmersVisibility(uiState.isLoading)
        recycler.setItems(uiState.reactions)
    }

    private fun handleNews(news: ReactionsNews) {
        when (news) {
            is ReactionsNews.OpenMain -> router.replaceScreen(Screens.MainScreen())
            is ReactionsNews.OpenChats -> router.replaceScreen(Screens.ChatsScreen())
            is ReactionsNews.OpenAccount -> router.replaceScreen(Screens.AccountScreen())
            is ReactionsNews.OpenBottomSheet -> showBottomSheet()
            is ReactionsNews.ShowError -> {
                if (::bottomSheet.isInitialized) {
                    bottomSheet.isCancelable = true
                    bottomSheet.dismiss()
                }
                showAlert(news.message)
            }
            is ReactionsNews.HideBottomSheet -> {
                bottomSheet.isCancelable = true
                bottomSheet.dismiss()
            }
        }
    }

    private fun showBottomSheet() {
        bottomSheet = ReplyOnReactionBottomSheetDialog(listener = this)
        bottomSheet.show(
            parentFragmentManager,
            ReplyOnReactionBottomSheetDialog::class.java.simpleName,
        )
    }

    override fun onReactionClick(message: String) {
        store.dispatch(ReactionsUiEvent.SendReplyOnReaction(message))
        bottomSheet.isCancelable = false
    }
}
