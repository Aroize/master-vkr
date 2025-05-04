package care.visify.ui.kit.util.formatter

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext

object PriceFormatter {

    enum class Currency { RUB }

    @ApplicationContext
    private lateinit var ctx: Context

    fun init(ctx: Context) {
        PriceFormatter.ctx = ctx
    }

    @JvmStatic
    fun formatPricing(price: Int): String {
        return price.toString()
            .reversed()
            .chunked(3)
            .joinToString(separator = " ")
            .reversed()
    }

    @JvmStatic
    fun formatCurrencyFrom(value: Int, currency: Currency = Currency.RUB): String {
        return "от ${formatPricing(value)} ₽"
    }

    @JvmStatic
    fun formatCurrency(value: Int, currency: Currency = Currency.RUB): String {
        return "${formatPricing(value)} ₽"
    }

    @JvmStatic
    fun formatPriceRange(from: Int, to: Int, currency: Currency = Currency.RUB): String {
        return "${formatPricing(from)} — ${formatPricing(to)} ₽"
    }

}