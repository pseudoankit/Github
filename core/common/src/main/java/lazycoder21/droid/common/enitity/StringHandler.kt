package lazycoder21.droid.common.enitity

import android.content.Context
import androidx.annotation.StringRes

sealed class StringHandler {

    data class NormalString(val string: String) : StringHandler()

    class ResourceString(
        @StringRes val stringId: Int, vararg val arguments: Any,
    ) : StringHandler()

    fun asString(context: Context): String {
        return when (this) {
            is NormalString -> this.string
            is ResourceString -> String.format(
                context.resources.getString(stringId), arguments
            )
        }
    }
}