package care.visify.core.storage

import android.webkit.MimeTypeMap
import java.io.File

val File.mimeType: String
    get() = MimeTypeMap.getFileExtensionFromUrl(toString())
            .ifBlank { null }
            ?.let { MimeTypeMap.getSingleton().getMimeTypeFromExtension(it) }
            ?: ""