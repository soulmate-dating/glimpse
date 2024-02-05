package ru.hse.glimpse.utils.views

import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView

fun makeTextLink(
    textView: TextView,
    string: String,
    underlined: Boolean = false,
    color: Int = Color.BLUE,
    action: (() -> Unit)? = null
) {
    val spannableString = SpannableString(textView.text)
    val clickableSpan = object : ClickableSpan() {
        override fun onClick(textView: View) {
            action?.invoke()
        }
        override fun updateDrawState(drawState: TextPaint) {
            super.updateDrawState(drawState)
            drawState.isUnderlineText = underlined
            drawState.color = color
        }
    }
    val index = spannableString.indexOf(string)
    spannableString.setSpan(clickableSpan, index, index + string.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    textView.text = spannableString
    textView.movementMethod = LinkMovementMethod.getInstance()
    textView.highlightColor = Color.TRANSPARENT
}
