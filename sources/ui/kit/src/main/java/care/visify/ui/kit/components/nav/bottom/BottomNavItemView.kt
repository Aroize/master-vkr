package care.visify.ui.kit.components.nav.bottom

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import care.visify.ui.kit.R
import ru.nikartm.support.ImageBadgeView

class BottomNavItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {
    private val imageView: ImageBadgeView
    private val textView: TextView

    init {
        addView(inflate(context, R.layout.bottom_nav_item, null))

        val typed =
            context.obtainStyledAttributes(attrs, R.styleable.BottomNavItemView, defStyleAttr, 0)

        val icon = typed.getDrawable(R.styleable.BottomNavItemView_android_src)
        imageView = findViewById<ImageBadgeView>(R.id.nav_icon).apply {
            setImageDrawable(icon)
            badgeTextFont = ResourcesCompat.getFont(context, R.font.gilroy_bold)
        }

        val text = typed.getString(R.styleable.BottomNavItemView_android_text)
        textView = findViewById<TextView>(R.id.nav_text)
            .apply { setText(text) }

        typed.recycle()
    }

    fun isNavItemSelected(selected: Boolean) {
        val color = when {
            selected -> R.color.label_primary
            else -> R.color.label_tertiary
        }.let { ContextCompat.getColor(context, it) }

        imageView.setColorFilter(color)
        textView.setTextColor(color)
    }

    fun setImage(@DrawableRes resId: Int) {
        imageView.setImageResource(resId)
    }

    fun setText(@StringRes resId: Int) {
        textView.setText(resId)
    }

    fun setBadge(value: Int) {
        imageView.badgeValue = value
    }
}