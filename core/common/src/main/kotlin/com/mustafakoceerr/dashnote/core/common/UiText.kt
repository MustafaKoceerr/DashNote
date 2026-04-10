package com.mustafakoceerr.dashnote.core.common

import android.content.Context
import androidx.annotation.StringRes

/**
 * ViewModel'dan UI'a metin gönderirken Context sızıntısını (Memory Leak) ve
 * Android bağımlılığını engellemek için kullanılan wrapper sınıf.
 */
sealed class UiText {
    data class DynamicString(val value: String) : UiText()

    class StringResource(
        @StringRes val resId: Int,
        vararg val args: Any
    ) : UiText()

    /**
     * UI katmanında bu metni çözmek için çağrılacak fonksiyon.
     */
    fun asString(context: Context): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> context.getString(resId, *args)
        }
    }
}