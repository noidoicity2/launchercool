package app.supersslc.ui.preferences.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import app.supersslc.preferences.BasePreferenceManager
import app.supersslc.preferences.getAdapter
import app.supersslc.ui.preferences.LocalNavController
import app.supersslc.ui.preferences.components.layout.PreferenceTemplate
import app.supersslc.ui.preferences.navigation.GeneralFontSelection

@Composable
fun FontPreference(
    fontPref: BasePreferenceManager.FontPref,
    label: String,
    modifier: Modifier = Modifier,
) {
    val navController = LocalNavController.current

    PreferenceTemplate(
        title = { Text(text = label) },
        description = {
            val font = fontPref.getAdapter().state.value
            Text(
                text = font.fullDisplayName,
                fontFamily = font.composeFontFamily,
            )
        },
        modifier = modifier
            .clickable { navController.navigate(route = GeneralFontSelection(fontPref.key)) },
    )
}
