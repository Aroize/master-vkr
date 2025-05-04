package care.visify.ui.kit.detailed

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import care.visify.ui.kit.containers.VisifyBox
import care.visify.ui.kit.containers.cellitem.SelectableListItem
import care.visify.ui.kit.modal.VisifyModalBottomSheet
import care.visify.ui.kit.modal.VisifySheetState
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.mainCellPrimary
import care.visify.ui.kit.theme.values.subheaderPrimary
import care.visify.ui.kit.util.cellShape
import kotlinx.collections.immutable.persistentListOf

sealed interface SharingItem {

    val title: String

    fun share(ctx: Context, data: String)

    data class Clipboard(override val title: String) : SharingItem {
        override fun share(ctx: Context, data: String) {
            val clipboardManager =
                ctx.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("label", data)
            clipboardManager.setPrimaryClip(clip)
        }
    }

    data class AppSharing(
        override val title: String,
        val pkg: List<String>,
    ) : SharingItem {
        override fun share(ctx: Context, data: String) {

            val intents = pkg.map {
                Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, data)
                    type = "text/plain"
                    `package` = it
                }
            }

            intents.forEach {
                kotlin.runCatching {
                    ctx.startActivity(it)
                }.onSuccess { return }
            }

            Toast.makeText(ctx, "Приложение не установлено на устройстве", Toast.LENGTH_SHORT)
                .show()

        }
    }

    data class Else(
        override val title: String,
    ) : SharingItem {
        override fun share(ctx: Context, data: String) {
            val intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, data)
                type = "text/plain"
            }
            ctx.startActivity(Intent.createChooser(intent, "Поделиться через"))
        }
    }
}

@Composable
fun SharingModalBottomSheet(state: VisifySheetState<String>) {

    VisifyModalBottomSheet(visifySheetState = state,
        header = {
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Поделиться",
                    style = VisifyTheme.visifyTypography.subheaderPrimary,
                    modifier = Modifier.padding(horizontal = VisifyTheme.padding._16dp)
                )
            }
        }) {

        Spacer(modifier = Modifier.height(16.dp))

        VisifyBox(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = VisifyTheme.padding._16dp),
            contentAlignment = Alignment.Center
        ) {
            QRCodeImage(source = state.unsafeData, size = 200.dp)
        }

        Spacer(modifier = Modifier.height(12.dp))

        val context = LocalContext.current

        val buttons = persistentListOf(
            SharingItem.Clipboard("Скопировать ссылку"),
            SharingItem.AppSharing(
                "Telegram",
                listOf(
                    "org.telegram.messenger",
                    "org.telegram.messenger.web"
                )
            ),
            SharingItem.AppSharing(
                "ВКонтакте",
                listOf("com.vkontakte.android", "com.vk.im")
            )
        )

        buttons.forEachIndexed { index, share ->
            SelectableListItem(
                text = share.title,
                textStyle = VisifyTheme.visifyTypography.mainCellPrimary,
                isSelected = false,
                hasBottomDivider = buttons.lastIndex != index,
                cellShape = cellShape(buttons, index),
                onClick = {
                    share.share(context, state.unsafeData)
                    state.hide()
                },
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        with(remember { SharingItem.Else("Еще...") }) {
            SelectableListItem(
                text = title,
                textStyle = VisifyTheme.visifyTypography.mainCellPrimary,
                isSelected = false,
                hasBottomDivider = false,
                cellShape = RoundedCornerShape(12.dp),
                onClick = {
                    share(context, state.unsafeData)
                    state.hide()
                },
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}