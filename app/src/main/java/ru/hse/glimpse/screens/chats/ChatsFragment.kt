package ru.hse.glimpse.screens.chats

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.hse.glimpse.R
import ru.hse.glimpse.databinding.FragmentChatsBinding
import ru.hse.glimpse.screens.chats.di.ChatsComponent
import ru.hse.glimpse.screens.chats.presentation.ChatsNews
import ru.hse.glimpse.screens.chats.ui.mapper.ChatsUiState
import ru.hse.glimpse.screens.chats.ui.recycler.ChatsViewHolderFactory
import ru.hse.glimpse.utils.views.FlowFragment
import ru.tinkoff.kotea.android.lifecycle.collectOnCreate
import ru.tinkoff.kotea.android.storeViaViewModel
import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped
import ru.tinkoff.mobile.tech.ti_recycler_coroutines.TiRecyclerCoroutines

@AndroidEntryPoint
class ChatsFragment : FlowFragment<ChatsComponent>(R.layout.fragment_chats) {

    private val binding by viewBinding(FragmentChatsBinding::bind)
    private val store by storeViaViewModel { component.createChatsStore() }
    private lateinit var recycler : TiRecyclerCoroutines<ViewTyped>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        store.collectOnCreate(
            fragment = this,
            uiStateMapper = component.uiStateMapper,
            stateCollector = ::render,
            newsCollector = ::handleNews,
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initRecycler()
        binding.bottomNavigation.selectedItemId = R.id.chats
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.appbar_menu, menu)
    }

    private fun initRecycler() {
        recycler = TiRecyclerCoroutines(
            binding.recycler,
            ChatsViewHolderFactory(),
        ) {
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        }
    }

    private fun initToolbar() {
        val activity = (requireActivity() as AppCompatActivity)
        with(activity) {
            setSupportActionBar(binding.toolbar)
            setHasOptionsMenu(true)
        }
    }

    private fun render(state: ChatsUiState) {
        recycler.setItems(state.items)
        if (state.isLoading) {
            binding.shimmers.startShimmer()
        } else {
            binding.shimmers.apply {
                stopShimmer()
                visibility = View.GONE
            }
            binding.recycler.visibility = View.VISIBLE
        }
    }

    private fun handleNews(news: ChatsNews) {
        TODO("Not yet implemented")
    }
}
