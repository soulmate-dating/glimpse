package ru.hse.glimpse.screens.prompts

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.terrakok.cicerone.Router
import dagger.hilt.android.AndroidEntryPoint
import ru.hse.glimpse.R
import ru.hse.glimpse.databinding.ActivityPromptsBinding
import ru.hse.glimpse.network.api.prompts.model.Prompt
import ru.hse.glimpse.screens.prompts.di.PromptsComponent
import ru.hse.glimpse.screens.prompts.presentation.PromptsNews
import ru.hse.glimpse.screens.prompts.presentation.PromptsNews.ShowError
import ru.hse.glimpse.screens.prompts.presentation.PromptsUiEvent.SendPromptClicked
import ru.hse.glimpse.screens.prompts.ui.dialogs.PromptsBottomSheetDialog
import ru.hse.glimpse.screens.prompts.ui.dialogs.PromptsBottomSheetDialog.PromptsBottomSheetDialogListener
import ru.hse.glimpse.screens.prompts.ui.mapper.PromptsUiState
import ru.hse.glimpse.screens.prompts.ui.recycler.PromptsViewHolderFactory
import ru.hse.glimpse.utils.views.showAlert
import ru.tinkoff.kotea.android.lifecycle.collectOnCreate
import ru.tinkoff.kotea.android.storeViaViewModel
import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped
import ru.tinkoff.mobile.tech.ti_recycler_coroutines.TiRecyclerCoroutines
import javax.inject.Inject

@AndroidEntryPoint
class PromptsActivity : AppCompatActivity(R.layout.activity_prompts),
    PromptsBottomSheetDialogListener {

    companion object {
        fun newIntent(context: Context) = Intent(context, PromptsActivity::class.java)
    }

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var component: PromptsComponent

    private val binding by viewBinding(ActivityPromptsBinding::bind)
    private val store by storeViaViewModel { component.createPromptsStore() }
    private lateinit var recycler: TiRecyclerCoroutines<ViewTyped>

    private lateinit var bottomSheet: PromptsBottomSheetDialog
    private lateinit var bottomSheetDialogListener: PromptsBottomSheetDialogListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        store.collectOnCreate(
            activity = this,
            uiStateMapper = component.createPromptsUiStateMapper,
            stateCollector = ::render,
            newsCollector = ::news,
        )

        initRecycler()
        binding.addTextPrompt.setOnClickListener { showBottomSheet() }
        bottomSheetDialogListener = this
    }

    private fun initRecycler() {
        recycler = TiRecyclerCoroutines(
            binding.recycler,
            PromptsViewHolderFactory(),
        )
    }

    override fun onResume() {
        super.onResume()
        window.statusBarColor = Color.WHITE
    }

    private fun render(uiState: PromptsUiState) {
        Log.d("My promptsRender", uiState.toString())
        recycler.setItems(uiState.items)
    }

    private fun news(news: PromptsNews) {
        when (news) {
            is ShowError -> showAlert(news.message)
        }
    }

    private fun showBottomSheet() {
        bottomSheet = PromptsBottomSheetDialog(listener = bottomSheetDialogListener)
        bottomSheet.show(supportFragmentManager, PromptsBottomSheetDialog::class.java.simpleName)
    }

    override fun sendPromptClicked(prompt: Prompt) {
        store.dispatch(SendPromptClicked(prompt))
        bottomSheet.dismiss()
    }

    override fun cancelClicked() {
        bottomSheet.dismiss()
    }
}
