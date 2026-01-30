package app.supersslc.gestures.handlers

import android.content.Context
import app.supersslc.LawnchairLauncher
import app.supersslc.preferences2.PreferenceManager2
import app.supersslc.qsb.LawnQsbLayout

class OpenSearchGestureHandler(context: Context) : GestureHandler(context) {

    override suspend fun onTrigger(launcher: LawnchairLauncher) {
        val prefs = PreferenceManager2.getInstance(launcher)
        val searchProvider = LawnQsbLayout.getSearchProvider(launcher, prefs)
        searchProvider.launch(launcher)
    }
}
