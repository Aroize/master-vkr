package care.visify.core.image.upload

fun interface UploadProgressListener {
    fun onUploadProgress(progress: Float)

    companion object EMPTY : UploadProgressListener {
        override fun onUploadProgress(progress: Float) = Unit
    }
}