package care.visify.core.image

import android.net.Uri
import androidx.core.net.toUri
import care.visify.api.models.common.ImageResponse
import java.io.File
import java.util.UUID

class Image(
    val remoteId: UUID?,
    val localFile: File?,
    var localUri: Uri?,
    val width: Int = 0,
    val height: Int = 0,
    val preview: String = ""
) {

    // empty
    constructor(): this(null, null, null)

    constructor(response: ImageResponse): this(
        remoteId = response.id,
        localFile = null,
        localUri = null,
        width = response.width,
        height = response.height,
        preview = response.preview
    )

    constructor(
        file: File,
    ): this(
        remoteId = null,
        localFile = file,
        localUri = file.toUri()
    )

    constructor(
        uri: Uri
    ): this(
        remoteId = null,
        localFile = null,
        localUri = uri
    )

    val isLocal: Boolean
        get() = localFile != null

    val isUri: Boolean
        get() = localUri != null

    val isRemote: Boolean
        get() = remoteId != null

    val isRemoteOnly: Boolean
        get() = remoteId != null && localUri == null && localFile == null

    val unsafeId: UUID
        get() = requireNotNull(remoteId)

    val unsafeFile: File
        get() = requireNotNull(localFile)

    val unsafeUri: Uri
        get() = requireNotNull(localUri)

    val aspectRatio: Float
        get() {
            if (height == 0) return 1f
            return width.toFloat() / height
        }

    fun mergeWithRemoteImage(remote: Image): Image {
        return Image(
            remoteId = remote.remoteId,
            localFile = localFile,
            localUri = localUri,
            width = remote.width,
            height = remote.height,
            preview = remote.preview
        )
    }
}