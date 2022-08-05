package lazycoder21.droid.common.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import lazycoder21.droid.common.enitity.StringHandler

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.showIf(it: Boolean) {
    if (it) show() else hide()
}

fun Context.showErrorMessage(message: StringHandler) {
    Toast.makeText(this, message.asString(this), Toast.LENGTH_SHORT).show()
}