package ru.hse.glimpse.screens.prompts

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.hse.glimpse.R
import ru.hse.glimpse.databinding.FragmentPromptsBinding
import ru.hse.glimpse.navigation.Screens
import ru.hse.glimpse.network.api.prompts.model.Prompt
import ru.hse.glimpse.network.api.prompts.model.Prompt.PromptType
import ru.hse.glimpse.screens.prompts.di.PromptsComponent
import ru.hse.glimpse.screens.prompts.presentation.PromptsNews
import ru.hse.glimpse.screens.prompts.presentation.PromptsNews.HideBottomSheet
import ru.hse.glimpse.screens.prompts.presentation.PromptsNews.OpenMainScreen
import ru.hse.glimpse.screens.prompts.presentation.PromptsNews.ShowError
import ru.hse.glimpse.screens.prompts.presentation.PromptsUiEvent.MainClicked
import ru.hse.glimpse.screens.prompts.presentation.PromptsUiEvent.SendPromptClicked
import ru.hse.glimpse.screens.prompts.ui.dialogs.PromptsBottomSheetDialog
import ru.hse.glimpse.screens.prompts.ui.dialogs.PromptsBottomSheetDialog.PromptsBottomSheetDialogListener
import ru.hse.glimpse.screens.prompts.ui.mapper.PromptsUiState
import ru.hse.glimpse.screens.prompts.ui.recycler.PromptsViewHolderFactory
import ru.hse.glimpse.utils.views.FlowFragment
import ru.hse.glimpse.utils.views.showAlert
import ru.tinkoff.kotea.android.lifecycle.collectOnCreate
import ru.tinkoff.kotea.android.storeViaViewModel
import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped
import ru.tinkoff.mobile.tech.ti_recycler_coroutines.TiRecyclerCoroutines

@AndroidEntryPoint
class PromptsFragment : FlowFragment<PromptsComponent>(R.layout.fragment_prompts),
        PromptsBottomSheetDialogListener {

    private val binding by viewBinding(FragmentPromptsBinding::bind)
    private val store by storeViaViewModel { component.createPromptsStore() }
    private lateinit var recycler: TiRecyclerCoroutines<ViewTyped>

    private lateinit var bottomSheet: PromptsBottomSheetDialog
    private lateinit var bottomSheetDialogListener: PromptsBottomSheetDialogListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        store.collectOnCreate(
            fragment = this,
            uiStateMapper = component.createPromptsUiStateMapper,
            stateCollector = ::render,
            newsCollector = ::news,
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        initRecycler()
        binding.addTextPrompt.setOnClickListener { showBottomSheet(type = PromptType.TEXT) }
        binding.addImagePrompt.setOnClickListener { showBottomSheet(type = PromptType.IMAGE) }
        bottomSheetDialogListener = this
    }

    private fun initToolbar() {
        val activity = (requireActivity() as AppCompatActivity)
        with(activity) {
            setSupportActionBar(binding.toolbar)
            setHasOptionsMenu(true)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.appbar_menu_prompts, menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.main -> store.dispatch(MainClicked)
        }
        return true
    }

    private fun initRecycler() {
        recycler = TiRecyclerCoroutines(
            binding.recycler,
            PromptsViewHolderFactory(),
        )
    }

    private fun render(uiState: PromptsUiState) {
        recycler.setItems(uiState.items)
    }

    private fun news(news: PromptsNews) {
        when (news) {
            is ShowError -> {
                if (::bottomSheet.isInitialized) {
                    bottomSheet.isCancelable = true
                    bottomSheet.dismiss()
                }
                showAlert(news.message)
            }
            is HideBottomSheet -> {
                bottomSheet.isCancelable = true
                bottomSheet.dismiss()
                binding.recycler.smoothScrollToPosition(recycler.adapter.items.size - 1)
            }
            is OpenMainScreen -> router.navigateTo(Screens.MainScreen())
        }
    }

    private fun showBottomSheet(type: PromptType) {
        bottomSheet = PromptsBottomSheetDialog(
            listener = bottomSheetDialogListener,
            type = type,
        )
        bottomSheet.show(parentFragmentManager, PromptsBottomSheetDialog::class.java.simpleName)
    }

    override fun sendPromptClicked(prompt: Prompt) {
        store.dispatch(SendPromptClicked(prompt))
        bottomSheet.isCancelable = false
    }
}
