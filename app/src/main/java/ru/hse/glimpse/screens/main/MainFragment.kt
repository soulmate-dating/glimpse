package ru.hse.glimpse.screens.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.hse.glimpse.R
import ru.hse.glimpse.databinding.FragmentMainBinding
import ru.hse.glimpse.navigation.Screens
import ru.hse.glimpse.screens.main.di.MainComponent
import ru.hse.glimpse.screens.main.presentation.MainNews
import ru.hse.glimpse.screens.main.presentation.MainUiEvent
import ru.hse.glimpse.screens.main.presentation.MainUiEvent.CancelClicked
import ru.hse.glimpse.screens.main.presentation.MainUiEvent.PullToRefresh
import ru.hse.glimpse.screens.main.ui.dialogs.SendReactionBottomSheetDialog
import ru.hse.glimpse.screens.main.ui.dialogs.SendReactionBottomSheetDialog.SendReactionBottomSheetDialogListener
import ru.hse.glimpse.screens.main.ui.mapper.MainUiState
import ru.hse.glimpse.screens.main.ui.recycler.MainViewHolderFactory
import ru.hse.glimpse.utils.views.FlowFragment
import ru.hse.glimpse.utils.views.showAlert
import ru.tinkoff.kotea.android.lifecycle.collectOnCreate
import ru.tinkoff.kotea.android.storeViaViewModel
import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped
import ru.tinkoff.mobile.tech.ti_recycler_coroutines.TiRecyclerCoroutines

@AndroidEntryPoint
class MainFragment : FlowFragment<MainComponent>(R.layout.fragment_main),
    SendReactionBottomSheetDialogListener {

    private val binding by viewBinding(FragmentMainBinding::bind)
    private val store by storeViaViewModel { component.createMainStore() }
    private lateinit var recycler: TiRecyclerCoroutines<ViewTyped>

    private lateinit var bottomSheet: SendReactionBottomSheetDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        store.collectOnCreate(
            fragment = this,
            uiStateMapper = component.mainUiStateMapper,
            stateCollector = ::render,
            newsCollector = ::handleNews,
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initBottomBar()
        initRecycler()
        binding.swipeRefreshLayout.setOnRefreshListener { store.dispatch(PullToRefresh) }
        binding.fab.setOnClickListener { store.dispatch(CancelClicked) }
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.appbar_menu_main, menu)
    }

    private fun initToolbar() {
        val activity = (requireActivity() as AppCompatActivity)
        with(activity) {
            setSupportActionBar(binding.toolbar)
            setHasOptionsMenu(true)
        }
    }

    private fun initBottomBar() {
        binding.bottomNavigation.selectedItemId = R.id.main

        binding.bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.chats -> {
                    store.dispatch(MainUiEvent.ChatsScreenClicked)
                    true
                }

                R.id.account -> {
                    store.dispatch(MainUiEvent.AccountScreenClicked)
                    true
                }

                R.id.liked -> {
                    store.dispatch(MainUiEvent.ReactionsScreenClicked)
                    true
                }

                else -> false
            }
        }
    }

    private fun initRecycler() {
        recycler = TiRecyclerCoroutines(
            binding.recycler,
            MainViewHolderFactory(
                onPromptClicked = { store.dispatch(MainUiEvent.PromptClicked(it)) },
            ),
        )
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

    private fun render(uiState: MainUiState) {
        binding.toolbar.title = uiState.firstName ?: "Oops!.."
        binding.swipeRefreshLayout.isRefreshing = uiState.isPullToRefreshRunning

        controlShimmersVisibility(uiState.isLoading)
        recycler.setItems(uiState.items)
    }

    private fun handleNews(news: MainNews) {
        when (news) {
            is MainNews.OpenChats -> router.navigateTo(Screens.ChatsScreen())
            is MainNews.OpenAccount -> router.navigateTo(Screens.AccountScreen())
            is MainNews.OpenReactions -> router.navigateTo(Screens.ReactionsScreen())
            is MainNews.ShowError -> {
                if (::bottomSheet.isInitialized) {
                    bottomSheet.isCancelable = true
                    bottomSheet.dismiss()
                }
                showAlert(news.message)
            }
            is MainNews.OpenReactionBottomSheet -> showBottomSheet()
            is MainNews.HideBottomSheet -> {
                bottomSheet.isCancelable = true
                bottomSheet.dismiss()
                store.dispatch(CancelClicked)
            }
            is MainNews.ScrollToTopOfScreen -> binding.recycler.smoothScrollToPosition(0)
        }
    }

    private fun showBottomSheet() {
        bottomSheet = SendReactionBottomSheetDialog(listener = this)
        bottomSheet.show(
            parentFragmentManager,
            SendReactionBottomSheetDialog::class.java.simpleName
        )
    }

    override fun sendReactionClicked(comment: String) {
        store.dispatch(MainUiEvent.ReactionSent(comment = comment))
        bottomSheet.isCancelable = false
    }
}
