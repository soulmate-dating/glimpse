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
import ru.hse.glimpse.screens.main.ui.mapper.MainUiState
import ru.hse.glimpse.screens.main.ui.recycler.MainViewHolderFactory
import ru.hse.glimpse.utils.views.FlowFragment
import ru.tinkoff.kotea.android.lifecycle.collectOnCreate
import ru.tinkoff.kotea.android.storeViaViewModel
import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped
import ru.tinkoff.mobile.tech.ti_recycler_coroutines.TiRecyclerCoroutines

@AndroidEntryPoint
class MainFragment : FlowFragment<MainComponent>(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)
    private val store by storeViaViewModel { component.createMainStore() }
    private lateinit var recycler : TiRecyclerCoroutines<ViewTyped>

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
                else -> false
            }
        }
    }

    private fun initRecycler() {
        recycler = TiRecyclerCoroutines(
            binding.recycler,
            MainViewHolderFactory(),
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
        controlShimmersVisibility(uiState.isLoading)
        recycler.setItems(uiState.items)
    }

    private fun handleNews(news: MainNews) {
        when (news) {
            is MainNews.OpenChats -> router.newRootScreen(Screens.ChatsScreen())
        }
    }
}
