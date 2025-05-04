package care.visify.ui.kit.order

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import care.visify.ui.icons.IconsR
import care.visify.ui.kit.components.button.VisifyButton
import care.visify.ui.kit.components.divider.VisifyDivider
import care.visify.ui.kit.modal.VisifyModalBottomSheet
import care.visify.ui.kit.modal.VisifySheetState
import care.visify.ui.kit.modal.headers.VisifyModalHeader
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.mainTextSecondary
import care.visify.ui.kit.theme.values.mainTextTertiary
import care.visify.ui.kit.theme.values.microPrimary
import care.visify.ui.kit.theme.values.microSecondary
import care.visify.ui.kit.theme.values.miniSecondary
import care.visify.ui.kit.theme.values.subheaderPrimary
import care.visify.ui.models.order.SucceedCreatedOrderUiModel


class OrderCreatedState(
    val title: String,
    val info: AnnotatedString,
    val notificationState: NotificationAbsentState,
    val notificationsEnabled: Boolean,
) {
    data class NotificationAbsentState(
        val title: String,
        val subtitle: String,
        val notification: String,
    )

    companion object {

        @Composable
        fun fromManualOrder(
            createdOrder: SucceedCreatedOrderUiModel,
        ): OrderCreatedState {
            val title = "Вы успешно записаны!"
            val info = buildAnnotatedString {
                withStyle(SpanStyle(color = VisifyTheme.colors.label.primary)) {
                    append("${createdOrder.organisation}\n")
                }
                withStyle(SpanStyle(color = VisifyTheme.colors.label.tertiary)) {
                    append("${createdOrder.time}\n${createdOrder.master}")
                }
            }

            val notificationTitle = "Мы напомним"
            val notificationSubtitle =
                "Разрешите уведомления и мы заботливо напомним за день до выбранного времени"
            val notificationInfo =
                "Не забудьте про ${createdOrder.favor}\n${createdOrder.time}\n${createdOrder.organisation}"

            return OrderCreatedState(
                title = title,
                info = info,
                notificationsEnabled = createdOrder.notificationsEnabled,
                notificationState = NotificationAbsentState(
                    title = notificationTitle,
                    subtitle = notificationSubtitle,
                    notification = notificationInfo
                )
            )
        }

        @Composable
        fun fromMarketOrder(
            succeedCreatedOrder: SucceedCreatedOrderUiModel,
        ): OrderCreatedState {
            val title = "Заказ успешно создан!"
            val info =
                AnnotatedString("Мастера уже начали получать заказы.\nВсе размещенные заказы можно посмотреть в Профиле и остановить.")

            val notificationTitle = "Не пропустите сообщения мастеров"
            val notificationSubtitle = "Включите уведомления, что бы узнать о новых сообщениях"

            val notificationText =
                "Не забудьте про ${succeedCreatedOrder.favor}\n${succeedCreatedOrder.time}\n${succeedCreatedOrder.organisation}"

            return OrderCreatedState(
                title = title,
                info = info,
                notificationsEnabled = succeedCreatedOrder.notificationsEnabled,
                notificationState = NotificationAbsentState(
                    title = notificationTitle,
                    subtitle = notificationSubtitle,
                    notification = notificationText
                )
            )
        }
    }
}

//todo rework
@Composable
fun CreatedOrderSheet(
    isManualOrder: Boolean,
    state: VisifySheetState<SucceedCreatedOrderUiModel>,
    onDismiss: () -> Unit = { },
) {

    val actualData = state.data ?: return

    val order = when {
        isManualOrder -> OrderCreatedState.fromManualOrder(
            createdOrder = actualData
        )

        else -> OrderCreatedState.fromMarketOrder(
            succeedCreatedOrder = actualData
        )
    }

    VisifyModalBottomSheet(
        visifySheetState = state,
        footer = { },
        header = { VisifyModalHeader(titleText = order.title) },
        onDismiss = onDismiss
    ) {

        OrderCreatedGraciasBlock(
            state = order
        )

        Spacer(
            modifier = if (order.notificationsEnabled)
                Modifier.size(30.dp)
            else
                Modifier.size(40.dp)
        )

        Box(
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {

            if (order.notificationsEnabled.not()) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    VisifyDivider(Modifier.fillMaxWidth())
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = order.notificationState.title,
                        style = VisifyTheme.visifyTypography.subheaderPrimary
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = order.notificationState.subtitle,
                        style = VisifyTheme.visifyTypography.mainTextSecondary
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(230.dp, 300.dp)
                                .paint(
                                    painter = painterResource(id = IconsR.phone),
                                    contentScale = ContentScale.FillBounds
                                )
                        )
                        Box(
                            modifier = Modifier
                                .padding(top = 68.dp)
                                .border(
                                    2.dp,
                                    VisifyTheme.colors.label.quaternary,
                                    RoundedCornerShape(12.dp)
                                )
                                .background(
                                    VisifyTheme.colors.label.white,
                                    RoundedCornerShape(12.dp)
                                )
                                .size(315.dp, 103.dp)
                                .align(Alignment.TopCenter)
                        ) {
                            OrderNotification(order)
                        }
                    }
                }
            }

            VisifyButton(
                label = "Отлично",
                modifier = Modifier
                    .padding(bottom = 36.dp)
                    .fillMaxWidth()
                    .height(56.dp)
                    .align(Alignment.BottomCenter),
                onClick = {
                    state.hide()
                    onDismiss()
                }
            )
        }
    }
}

@Composable
fun OrderCreatedGraciasBlock(state: OrderCreatedState) {
    val content: @Composable () -> Unit = {
        Text(
            text = "\uD83C\uDF89",
            fontSize = if (state.notificationsEnabled) 73.sp else 50.sp
        )
        Spacer(modifier = Modifier.size(24.dp))
        OrderCreatedInfoBlock(state)
    }

    when {
        state.notificationsEnabled -> Column(
            modifier = Modifier.padding(horizontal = 16.dp),
            content = { content() }
        )

        else -> Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp),
            content = { content() }
        )
    }
}

@Composable
fun OrderCreatedInfoBlock(order: OrderCreatedState) {
    Text(
        text = order.info,
        style = VisifyTheme.visifyTypography.mainTextTertiary
    )
}

@Composable
fun OrderNotification(order: OrderCreatedState) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(13.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(18.dp)
                        .background(
                            VisifyTheme.colors.label.active,
                            RoundedCornerShape(4.dp)
                        )
                )
                Text(
                    text = "Салоны",
                    style = VisifyTheme.visifyTypography.microPrimary
                )
            }

            Text(
                text = "сейчас",
                style = VisifyTheme.visifyTypography.microSecondary
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = order.notificationState.notification,
            style = VisifyTheme.visifyTypography.miniSecondary
        )
    }
}