package ru.hse.glimpse.utils.views

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Router
import ru.hse.glimpse.R
import ru.hse.glimpse.navigation.Screens
import ru.hse.glimpse.utils.di.BaseComponent
import javax.inject.Inject

open class FlowFragment<Component: BaseComponent>(
    @LayoutRes contentLayoutId: Int
) : Fragment(contentLayoutId) {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var component: Component

    protected fun showAlertAndQuit(message: String? = "Some alert") {
        showAlert(message = message ?: getString(R.string.sample_error_message))
        router.newRootChain(Screens.InOrUpScreen())
    }
}
