package ru.hse.glimpse.utils.views

import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import kotlinx.parcelize.Parcelize

@Parcelize
data class MyFragmentArgs(
    val contentId: Long,
    val otherData: String
) : IArgs

interface IArgs : Parcelable
interface IFragmentArgs<ARGS : IArgs>

private const val BUNDLE_ARGUMENTS_KEY = "arguments"

fun <FRAGMENT, ARGS : IArgs> FRAGMENT.setArgs(args: ARGS): FRAGMENT where FRAGMENT : Fragment, FRAGMENT : IFragmentArgs<ARGS> {
    return apply {
        arguments = Bundle().apply {
            putParcelable(BUNDLE_ARGUMENTS_KEY, args)
        }
    }
}

@MainThread
inline fun <FRAGMENT, reified ARGS : IArgs> FRAGMENT.args(): ArgsLazy<ARGS> where FRAGMENT : Fragment, FRAGMENT : IFragmentArgs<ARGS> = ArgsLazy {
    arguments ?: throw IllegalStateException("Fragment $this has null arguments")
}

class ArgsLazy<ARGS : IArgs>(
    private val argumentsProducer: () -> Bundle
) : Lazy<ARGS> {
    private var cached: ARGS? = null

    override val value: ARGS
        get() {
            var args = cached
            if (args == null) {
                val arguments = argumentsProducer()

                args = arguments.getParcelable(BUNDLE_ARGUMENTS_KEY)!!
                cached = args
            }
            return args
        }

    override fun isInitialized(): Boolean = cached != null
}
