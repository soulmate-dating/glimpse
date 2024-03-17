package ru.hse.glimpse.utils.views

import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar

fun ViewBinding.showAlert(message: String = "Alert message") {
    Snackbar.make(this.root, message, Snackbar.LENGTH_SHORT).show()
}
