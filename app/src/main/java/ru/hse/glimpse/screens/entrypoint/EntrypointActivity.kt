package ru.hse.glimpse.screens.entrypoint

import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.hilt.android.AndroidEntryPoint
import ru.hse.glimpse.R
import ru.hse.glimpse.utils.navigation.GlimpseAppNavigator
import javax.inject.Inject

@AndroidEntryPoint
class EntrypointActivity : AppCompatActivity(R.layout.activity_entrypoint) {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: Router

    private val navigator = GlimpseAppNavigator(this, R.id.container)

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onStart() {
        super.onStart()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, EntrypointFragment())
            .commit()
    }
}
