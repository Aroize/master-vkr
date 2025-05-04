package care.visify.ui.kit.util.formatter

import android.content.Context
import care.visify.ui.kit.R
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.DayOfWeek
import java.util.Locale

object DayOfWeekFormatter {

    @ApplicationContext
    private lateinit var ctx: Context

    fun init(ctx: Context) {
        this.ctx = ctx
    }

    @JvmStatic
    fun formatDayOfWeek(
        dayOfWeek: DayOfWeek,
    ): String {
        val resources = ctx.resources
        return resources.getStringArray(R.array.days_of_week)[dayOfWeek.value - 1].replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.ROOT
            ) else it.toString()
        }
    }
}

fun DayOfWeek.toUiString(): String = DayOfWeekFormatter.formatDayOfWeek(this)