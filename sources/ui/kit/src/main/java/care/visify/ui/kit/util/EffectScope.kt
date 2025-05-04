package care.visify.ui.kit.util

import android.content.Context

interface EffectScope {
    val context: Context
}

internal class EffectScopeImpl(override val context: Context): EffectScope

fun EffectScope.asString(uiText: care.visify.core.util.UiText) = uiText.asString(context)