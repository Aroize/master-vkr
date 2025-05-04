package care.visify.ui.kit.util.formatter

import android.content.Context

object FormattersInitializer {
    fun init(ctx: Context) {
        PriceFormatter.init(ctx)
        TimeFormatter.init(ctx)
        RatingFormatter.init(ctx)
        DayOfWeekFormatter.init(ctx)
    }
}