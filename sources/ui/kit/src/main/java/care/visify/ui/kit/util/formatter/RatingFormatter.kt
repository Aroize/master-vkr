package care.visify.ui.kit.util.formatter

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext

object RatingFormatter {

    @ApplicationContext
    private lateinit var ctx: Context

    fun init(ctx: Context) {
        RatingFormatter.ctx = ctx
    }

    @JvmStatic
    fun formatRatingRange(from: Float?, to: Float?): String {
        val noMinRating = from == null
        val noMaxRating = to == null
        val rating = buildString {
            when {
                noMinRating && noMaxRating -> append("Любой рейтинг")
                noMinRating -> {
                    append("до ")
                    append(requireNotNull(to).formatRating())
                    append(" Звезд")
                }

                noMaxRating -> {
                    append("от ")
                    append(requireNotNull(from).formatRating())
                    append(" Звезд")
                }

                else -> {
                    append(requireNotNull(from).formatRating())
                    append("—")
                    append(requireNotNull(to).formatRating())
                    append(" Звезды")
                }
            }
        }

        return rating
    }

    private fun Float.formatRating() = "%.1f".format(this)
        .replace('.', ',')

}