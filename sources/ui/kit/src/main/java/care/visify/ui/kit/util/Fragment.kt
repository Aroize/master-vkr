package care.visify.ui.kit.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.withCreationCallback

inline fun<reified VM: ViewModel, F> Fragment.visifyViewModels(noinline creator: (F) -> VM): Lazy<VM> {
    return viewModels(
        extrasProducer = {
            defaultViewModelCreationExtras.withCreationCallback(creator)
        }
    )
}