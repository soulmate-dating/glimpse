package ru.hse.glimpse.utils.views

import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.google.android.material.button.MaterialButton
import ru.hse.glimpse.R

fun MaterialButton.setShowProgress(showProgress: Boolean?) {
    icon = if (showProgress == true) {
        CircularProgressDrawable(context!!).apply {
            setStyle(CircularProgressDrawable.DEFAULT)
            setColorSchemeColors(ContextCompat.getColor(context!!, R.color.md_theme_light_onPrimary))
            start()
        }
    } else null
    if (icon != null) {
        icon.callback = object : Drawable.Callback {
            override fun unscheduleDrawable(who: Drawable, what: Runnable) { }

            override fun invalidateDrawable(who: Drawable) {
                this@setShowProgress.invalidate()
            }

            override fun scheduleDrawable(who: Drawable, what: Runnable, `when`: Long) { }
        }
    }
}
