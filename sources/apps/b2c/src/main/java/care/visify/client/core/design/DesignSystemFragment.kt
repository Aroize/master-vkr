package care.visify.client.core.design

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.visify.client.R
import care.visify.core.image.Image
import care.visify.core.navigator.api.VisifyFragmentScreen
import care.visify.core.navigator.api.VisifyScreen
import care.visify.feature.master.api.MasterScreen
import care.visify.ui.icons.IconsR
import care.visify.ui.kit.components.button.VisifyTextButton
import care.visify.ui.kit.components.calendar.MonthItem
import care.visify.ui.kit.components.calendar.MultipleMonthValueChanger
import care.visify.ui.kit.components.input.SearchField
import care.visify.ui.kit.components.input.VisifyInputField
import care.visify.ui.kit.components.toggle.VisifyToggle
import care.visify.ui.kit.detailed.favor.FavorExpandableItem
import care.visify.ui.kit.detailed.master.MasterItem
import care.visify.ui.kit.detailed.master.MasterRating
import care.visify.ui.kit.detailed.organisation.OrganisationItem
import care.visify.ui.kit.fragment.VisifyFragment
import care.visify.ui.kit.modal.VisifyModalBottomSheet
import care.visify.ui.kit.modal.footers.PrimaryFooterButton
import care.visify.ui.kit.modal.footers.SecondaryFooterButton
import care.visify.ui.kit.modal.footers.VisifyDoubleBtnFooter
import care.visify.ui.kit.modal.headers.VisifyModalHeader
import care.visify.ui.kit.modal.rememberVisifySheetState
import care.visify.ui.kit.overlay.navigator.LinkOverlayScreen
import care.visify.ui.kit.overlay.navigator.rememberOverlayNavigator
import care.visify.ui.kit.theme.VisifyTheme
import care.visify.ui.kit.theme.values.buttonPrimary
import care.visify.ui.kit.theme.values.mainCellPrimary
import care.visify.ui.kit.theme.values.mainCellTertiary
import care.visify.ui.kit.util.cell
import care.visify.ui.kit.util.clickableNoInteraction
import care.visify.ui.models.calendar.CalendarMonthUiModel
import care.visify.ui.models.favor.FavorUiModel
import care.visify.ui.models.favor.FavorsByCategoryUiModel
import care.visify.ui.models.master.MasterUiModel
import care.visify.ui.models.org.OrganisationUiModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import java.util.UUID
import androidx.compose.foundation.Image as ComposeImage

object DesignSystemScreen : VisifyScreen()

val DESIGN_SYSTEM_SCREEN: Map<Class<*>, (VisifyScreen) -> VisifyFragmentScreen> = mapOf(
    DesignSystemScreen::class.java to {
        VisifyFragmentScreen(it) { DesignSystemFragment() }
    }
)

class DesignSystemFragment : VisifyFragment() {

    @Preview
    @Composable
    override fun FragmentContent() {

        val bottomSheet = rememberVisifySheetState<List<String>>()

        Column(
            modifier = Modifier
                .background(Color.LightGray)
                .verticalScroll(rememberScrollState())
        ) {

            val master = MasterUiModel(
                id = 123,
                name = "Свинка Пеппа",
                surname = "З.",
                rating = 4.5f,
                profession = "Педикюрщик, Лашмейкер, Мама Свин",
                avatar = Image()
            )

            Spacer(modifier = Modifier.height(20.dp))
            MasterItem(
                master = master,
                shape = RoundedCornerShape(6.dp),
                hasDivider = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                masterRating = MasterRating.PROFESSION
            )

            Spacer(modifier = Modifier.height(20.dp))
            MasterItem(
                master = master,
                shape = RoundedCornerShape(6.dp),
                hasDivider = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                masterRating = MasterRating.END
            )

            Spacer(modifier = Modifier.height(20.dp))
            MasterItem(
                master = master,
                shape = RoundedCornerShape(6.dp),
                hasDivider = false,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                masterRating = MasterRating.ABSENT
            )


            var toggleState by remember { mutableStateOf(false) }

            // toggle
            VisifyToggle(
                text = "Toggle",
                onClick = { toggleState = it },
                modifier = Modifier.padding(16.dp),
                enabled = toggleState
            )

            // Organisation
            val organisation = OrganisationUiModel(
                id = 1,
                logo = Image(
                    remoteId = UUID.randomUUID(), null, null
                ),
                name = "The Girls Hairdressers",
                address = "ул. Саксаганского, 37",
                favors = "Брови, Макияж, Маникюр, Парикмахер, Педикюр",
                rating = 4.7f,
                totalRatings = 123,
                avgBill = 1500,
                avatars = listOf(
                    Image(
                        remoteId = UUID.fromString("ef883a75-7fa9-403e-aa92-21a682e9ebbc"),
                        null,
                        null
                    ),
                    Image(
                        remoteId = UUID.fromString("ef883a75-7fa9-403e-aa92-21a682e9ebbc"),
                        null,
                        null
                    ),
                    Image(
                        remoteId = UUID.fromString("ef883a75-7fa9-403e-aa92-21a682e9ebbc"),
                        null,
                        null
                    ),
                    Image(
                        remoteId = UUID.fromString("ef883a75-7fa9-403e-aa92-21a682e9ebbc"),
                        null,
                        null
                    ),
                    Image(
                        remoteId = UUID.fromString("ef883a75-7fa9-403e-aa92-21a682e9ebbc"),
                        null,
                        null
                    ),
                ),
                commentText = "Я в восторге, отличные мастера",
                operatingTime = "Открыто до 19:00",
                isFavourite = true
            )
            OrganisationItem(organisation = organisation) { }

            Spacer(modifier = Modifier.height(16.dp))

            // Search Bar
            var query by remember { mutableStateOf(TextFieldValue("")) }
            SearchField(
                query = query,
                onValueChanged = { query = it },
                onSearchActive = { },
                modifier = Modifier
                    .padding(16.dp)
                    .height(52.dp)
                    .background(
                        color = VisifyTheme.colors.frame.white,
                        shape = RoundedCornerShape(6.dp)
                    ),
                trailing = {
                    ComposeImage(
                        painter = painterResource(id = IconsR.ic_close_search_18),
                        contentDescription = "Close Search",
                        modifier = Modifier
                            .padding(it)
                            .clickableNoInteraction { query = TextFieldValue("") },
                    )
                },
                placeholder = {
                    Text(
                        text = "Поиск тут",
                        style = VisifyTheme.visifyTypography.mainCellTertiary,
                        modifier = Modifier.padding(it)
                    )
                },
                stopper = {
                    Text(
                        text = stringResource(id = R.string.cancel),
                        style = VisifyTheme.visifyTypography.buttonPrimary,
                        modifier = Modifier
                            .padding(end = 16.dp)
                    )
                }
            )

            val calendarMonthUiModel = remember { mutableStateOf(CalendarMonthUiModel.default()) }
            val changer = MultipleMonthValueChanger(persistentListOf(calendarMonthUiModel))
            val state by calendarMonthUiModel

            MonthItem(
                monthModel = state,
                onValueChanged = changer,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(VisifyTheme.colors.frame.white)
            )

            Spacer(modifier = Modifier.height(24.dp))

            val favors = buildList {
                repeat(5) {
                    add(FavorUiModel(0, "Окрашивание ресниц краской", "490 ₽", "1 час"))
                }
            }.toPersistentList()

            FavorExpandableItem(
                FavorsByCategoryUiModel(
                    "Популярное",
                    favors
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .cell()
            )

            Spacer(modifier = Modifier.height(24.dp))


            var textInput by remember { mutableStateOf("") }

            VisifyInputField(
                value = textInput,
                onValueChange = { textInput = it },
                hint = "Mask",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
                    .background(VisifyTheme.colors.frame.white, RoundedCornerShape(6.dp))
                    .padding(bottom = 12.dp)
            )



            Spacer(modifier = Modifier.height(24.dp))

            VisifyTextButton(text = "Click Me") {
                bottomSheet.show(
                    content = listOf(
                        "ABC", "CBA", "ABC", "CBA", "ABC", "CBA", "ABC", "CBA"
                    )
                )
            }

            val overlay = rememberOverlayNavigator(target = MasterScreen(Int.MIN_VALUE))

            VisifyTextButton(text = "Master Me") {
                overlay.show(MasterScreen(masterId = 1018))
            }

            LinkOverlayScreen(navigator = overlay)

            Spacer(modifier = Modifier.height(24.dp))
        }



        VisifyModalBottomSheet(
            visifySheetState = bottomSheet,
            header = {
                VisifyModalHeader(
                    titleText = "Категории",
                    subtitleText = "Можно выбрать несколько"
                )
            },
            footer = {

                VisifyDoubleBtnFooter(
                    right = PrimaryFooterButton(
                        text = "Продолжить",
                        color = VisifyTheme.colors.label.active,
                        onClick = { }
                    ),
                    left = SecondaryFooterButton(
                        text = "Назад",
                        iconRes = IconsR.ic_back_24,
                        isVisible = true,
                        onClick = { }
                    )
                )
            }
        ) {
            repeat(10) {
                bottomSheet.unsafeData.forEach { text ->
                    Text(
                        text = text,
                        style = VisifyTheme.visifyTypography.mainCellPrimary,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}
