package care.visify.ui.kit.components.map

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraListener
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.CircleMapObject
import com.yandex.mapkit.map.ClusterizedPlacemarkCollection
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.map.MapObjectTapListener
import com.yandex.mapkit.map.MapObjectVisitor
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.mapkit.map.PolygonMapObject
import com.yandex.mapkit.map.PolylineMapObject
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider
import care.visify.ui.kit.R
import care.visify.ui.kit.util.dpInt


private const val MAP_SMOOTH_ANIMATION_DURATION = 1F

data class MapPoint(
    val id: Int = -1,
    val lat: Double,
    val lon: Double,
    @DrawableRes val icon: Int = R.drawable.ic_pin_active_128
)

data class MapSettings(
    val zoom: Float,
    val azimuth: Float,
    val tilt: Float,
    val isZoomGesturesEnabled: Boolean = false,
    val isScrollGesturesEnabled: Boolean = false
) {
    companion object {
        fun default() = MapSettings(14f, 0f, 0f)

        fun defaultWithTouch(zoom: Float = 14f) = default().copy(
            zoom = zoom,
            isScrollGesturesEnabled = true,
            isZoomGesturesEnabled = true
        )
    }
}


/**
 * @param onMarkTapped -> Lat, Lon, Zoom, MapPoint
 */
@Composable
fun MapItem(
    cameraPosition: MapPoint,
    modifier: Modifier = Modifier,
    animatePosition: Boolean = false,
    marks: List<MapPoint> = emptyList(),
    settings: MapSettings = MapSettings.default(),
    onMarkTapped: (Double, Double, Float, MapPoint) -> Boolean = { _, _, _, _, -> false }
) {

    val lifecycle = LocalLifecycleOwner.current.lifecycle

    DisposableEffect(lifecycle) {

        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_START -> MapKitFactory.getInstance().onStart()
                Lifecycle.Event.ON_STOP -> MapKitFactory.getInstance().onStop()
                else -> Unit
            }
        }

        lifecycle.addObserver(observer)

        onDispose {
            lifecycle.removeObserver(observer)
        }
    }

    val initialCameraPosition = CameraPosition(
        cameraPosition.toPoint(),
        settings.zoom,
        settings.azimuth,
        settings.tilt
    )

    var actualCameraPosition by remember { mutableStateOf(initialCameraPosition) }

    val mapObjectTapListener = remember {
        MapObjectTapListener { mark, _ ->
            onMarkTapped(
                actualCameraPosition.target.latitude,
                actualCameraPosition.target.longitude,
                actualCameraPosition.zoom,
                (mark.userData as MapPoint)
            )
        }
    }

    val cameraListener = remember {
        CameraListener { _, position, _, _ ->
            actualCameraPosition = position
        }
    }

    AndroidView(
        factory = {
            MapView(it).apply {
                mapWindow.map.isZoomGesturesEnabled = settings.isZoomGesturesEnabled
                mapWindow.map.isScrollGesturesEnabled = settings.isScrollGesturesEnabled
                mapWindow.map.addCameraListener(cameraListener)
            }
        },
        modifier = modifier,
        update = {
            updateMarks(it, marks, mapObjectTapListener)
            when {
                animatePosition -> it.mapWindow.map.move(
                    initialCameraPosition,
                    Animation(Animation.Type.SMOOTH, MAP_SMOOTH_ANIMATION_DURATION),
                    null
                )
                else -> it.mapWindow.map.move(initialCameraPosition)
            }
        }
    )
}

private fun updateMarks(
    mapView: MapView,
    marks: List<MapPoint>,
    tapListener: MapObjectTapListener
) {
    val ctx = mapView.context
    val map = mapView.mapWindow.map
    val handledMarks = mutableListOf<MapPoint>()
    val placemarksToRemove = mutableListOf<PlacemarkMapObject>()
    val placemarksToUpdate = mutableListOf<PlacemarkMapObject>()
    val collection = map.mapObjects
    val visitor = PlacemarkObjectVisitor(
        onPlacemark = { placemark ->
            val mark = placemark.userData as MapPoint
            val newMark = marks.find { it.id == mark.id } ?: run {
                placemarksToRemove += placemark
                return@PlacemarkObjectVisitor
            }
            if (newMark == mark) {
                handledMarks += mark
            } else {
                placemarksToUpdate += placemark.apply { userData = newMark }
            }
        },
        onEnd = {
            placemarksToRemove.forEach { collection.remove(it) }
            val unhandledMarks = marks.filter { it !in handledMarks }
            unhandledMarks.forEach { mark ->
                collection.addVisifyMark(ctx, mark, tapListener)
            }
            placemarksToUpdate.forEach { it.update(ctx) }
        }
    )
    collection.traverse(visitor)
}

val PlacemarkMapObject.visifyMark: MapPoint
    get() = userData as MapPoint

private fun MapObjectCollection.addVisifyMark(
    ctx: Context,
    mark: MapPoint,
    tapListener: MapObjectTapListener
) {
    addPlacemark()
        .apply {
            userData = mark
            addTapListener(tapListener)

            update(ctx)
        }
}

private fun PlacemarkMapObject.update(ctx: Context) {
    geometry = visifyMark.toPoint()
    val drawable = ContextCompat.getDrawable(ctx, visifyMark.icon) as Drawable
    val bitmap = Bitmap.createBitmap(
        32.dpInt(ctx), 32.dpInt(ctx), Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)

    setIcon(ImageProvider.fromBitmap(bitmap))
}

private fun MapPoint.toPoint() = Point(lat, lon)

class PlacemarkObjectVisitor(
    private val onPlacemark: (PlacemarkMapObject) -> Unit,
    private val onEnd: () -> Unit
): MapObjectVisitor {

    override fun onPlacemarkVisited(placemark: PlacemarkMapObject) = onPlacemark(placemark)

    override fun onCollectionVisitEnd(collection: MapObjectCollection) = onEnd()

    override fun onPolylineVisited(p0: PolylineMapObject) = Unit
    override fun onPolygonVisited(p0: PolygonMapObject) = Unit

    override fun onCircleVisited(p0: CircleMapObject) = Unit
    override fun onCollectionVisitStart(p0: MapObjectCollection): Boolean = true

    override fun onClusterizedCollectionVisitStart(p0: ClusterizedPlacemarkCollection): Boolean = false
    override fun onClusterizedCollectionVisitEnd(p0: ClusterizedPlacemarkCollection) = Unit
}