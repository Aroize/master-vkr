package care.visify.core.arch.util

import care.visify.core.arch.VisifyViewModel
import care.visify.core.util.UiText

sealed interface ViewProperty<out R> {
    data class Content<out T>(val content: T) : ViewProperty<T>
    data object Loading : ViewProperty<Nothing>
    data class Error(val errorMessage: UiText, val error: Throwable? = null) : ViewProperty<Nothing>

    val isLoading
        get() = this is Loading

    val isContent
        get() = this is Content

    val isError
        get() = this is Error
}

inline fun <R> ViewProperty<R>.requireContent(action: (R) -> Unit){
    safeContent?.let {
        action(it)
    }
}

val <R> ViewProperty<R>.safeContent: R?
    get() = (this as? ViewProperty.Content)?.content

fun VisifyViewModel<*, *, *>.loading() = ViewProperty.Loading

fun <T> VisifyViewModel<*, *, *>.content(content: T) = ViewProperty.Content(content)

fun VisifyViewModel<*, *, *>.failure(errorMessage: UiText, error: Throwable? = null) =
    ViewProperty.Error(errorMessage, error)
