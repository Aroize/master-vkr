package care.visify.ui.kit.theme.values

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

internal val baseCornerShape = CornerShape(
    rounded6dp = RoundedCornerShape(6.dp),
    roundedTop6dp = RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp),
    roundedBottom6dp = RoundedCornerShape(bottomStart = 6.dp, bottomEnd = 6.dp),
    circle = CircleShape
)