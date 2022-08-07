package lazycoder21.droid.common.utils

import android.content.Context
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.showIf(it: Boolean) {
    if (it) show() else hide()
}

fun Context.showErrorMessage(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun TextView.attributedString(@StringRes id: Int, vararg args: Any) {
    text = String.format(
        resources.getString(id), *args
    )
}