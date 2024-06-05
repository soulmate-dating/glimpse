package ru.hse.glimpse.utils.views

import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.showAlert(message: String?) {
    Snackbar.make(this.requireView(), message ?: "Alert message", Snackbar.LENGTH_SHORT).show()
}
