package ru.hse.glimpse.root

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.hse.glimpse.R
import ru.hse.glimpse.databinding.ActivityMainBinding
import ru.hse.glimpse.main.di.MainComponent
import ru.hse.glimpse.main.presentation.news.MainNews
import ru.hse.glimpse.main.presentation.state.MainState
import ru.tinkoff.kotea.android.lifecycle.collectOnCreate
import ru.tinkoff.kotea.android.storeViaViewModel
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    @Inject lateinit var component: MainComponent
    private val store by storeViaViewModel { component.getMainStore() }

    private val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        store.collectOnCreate(
            lifecycleOwner = this,
            stateCollector = ::render,
            newsCollector = ::handleNews,
        )
    }

    private fun render(state: MainState) {
        binding.information.text = state.textToShow
    }

    private fun handleNews(news: MainNews) {
        when (news) {
            is MainNews.ShowErrorToast -> {
                Toast.makeText(this, news.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
