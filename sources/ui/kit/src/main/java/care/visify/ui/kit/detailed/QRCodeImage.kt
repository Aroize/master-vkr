package care.visify.ui.kit.detailed

import android.graphics.Bitmap
import android.graphics.Color.BLACK
import android.graphics.Color.WHITE
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


internal object QRCodeHelper {
    fun newEncodeRequest(
        scope: CoroutineScope,
        source: String,
        width: Int,
        height: Int,
    ): State<Bitmap?> {
        val result = mutableStateOf<Bitmap?>(null)
        scope.launch(Dispatchers.IO) {
            val bitMatrix = runCatching {
                MultiFormatWriter()
                    .encode(source, BarcodeFormat.QR_CODE, width, height, null)
            }.getOrNull()
            bitMatrix ?: return@launch

            val w = bitMatrix.width
            val h = bitMatrix.height
            val pixels = IntArray(w * h)

            for (y in 0 until h) {
                val offset = y * w
                for (x in 0 until w) {
                    pixels[offset + x] = if (bitMatrix.get(x, y)) {
                        BLACK
                    } else {
                        WHITE
                    }
                }
            }

            val bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
            bitmap.setPixels(pixels, 0, width, 0, 0, w, h)

            result.value = bitmap

        }
        return result
    }
}

@Composable
fun QRCodeImage(
    source: String,
    size: Dp
) {
    val coroutineScope = rememberCoroutineScope()

    val sizePx = with(LocalDensity.current) { size.toPx().toInt() }

    val qrBitmap by remember {
        QRCodeHelper.newEncodeRequest(coroutineScope, source, sizePx, sizePx)
    }

    qrBitmap?.let { bitmap ->
        Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = null,
            modifier = Modifier.size(size)
        )
    }
}