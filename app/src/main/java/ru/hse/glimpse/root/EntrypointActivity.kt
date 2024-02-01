package ru.hse.glimpse.root

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import dagger.hilt.android.AndroidEntryPoint
import ru.hse.glimpse.R
import ru.hse.glimpse.entrypoint.di.EntrypointComponent
import ru.hse.glimpse.entrypoint.presentation.news.EntrypointNews
import ru.tinkoff.kotea.android.lifecycle.collectOnCreate
import ru.tinkoff.kotea.android.storeViaViewModel
import javax.inject.Inject

@AndroidEntryPoint
class EntrypointActivity : AppCompatActivity(R.layout.activity_entrypoint) {

    @Inject
    lateinit var component: EntrypointComponent
    private val store by storeViaViewModel { component.getMainStore() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        store.collectOnCreate(
            lifecycleOwner = this,
            stateCollector = null,
            newsCollector = ::handleNews,
        )
    }

    private fun handleNews(news: EntrypointNews) {
        when (news) {
            is EntrypointNews.ShowErrorToast -> {
                Toast.makeText(this, news.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
