package app.supersslc.smartspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import app.supersslc.ui.preferences.PreferenceActivity
import app.supersslc.ui.preferences.navigation.SmartspaceWidget

class SmartspacePreferencesShortcut : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(PreferenceActivity.createIntent(this, SmartspaceWidget))
        finish()
    }
}
