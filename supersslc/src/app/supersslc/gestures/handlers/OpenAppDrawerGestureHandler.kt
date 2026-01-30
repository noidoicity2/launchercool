package app.supersslc.gestures.handlers

import android.content.Context
import app.supersslc.LawnchairLauncher
import app.supersslc.animateToAllApps

open class OpenAppDrawerGestureHandler(context: Context) : GestureHandler(context) {

    override suspend fun onTrigger(launcher: LawnchairLauncher) {
        launcher.animateToAllApps()
    }
}
