package care.visify.core.image.upload

import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okio.Buffer
import okio.BufferedSink
import okio.ForwardingSink
import okio.Sink
import okio.buffer
import java.io.IOException

class MediaRequestBodyWithProgress(
    private val multipart: MultipartBody,
    private val listener: UploadProgressListener = UploadProgressListener.EMPTY
) : RequestBody() {

    @Throws(IOException::class)
    override fun contentLength(): Long {
        return multipart.contentLength()
    }

    override fun contentType(): MediaType {
        return multipart.contentType()
    }

    override fun writeTo(sink: BufferedSink) {
        val counterSink = CounterSink(sink)
        val bufferedSink = counterSink.buffer()
        multipart.writeTo(bufferedSink)
        bufferedSink.flush()
    }

    private inner class CounterSink(delegate: Sink): ForwardingSink(delegate) {

        private var bytesWritten = 0L

        override fun write(source: Buffer, byteCount: Long) {
            bytesWritten += byteCount
            super.write(source, byteCount)
            listener.onUploadProgress(bytesWritten.toFloat() / contentLength())
            delegate.flush()
        }
    }
}