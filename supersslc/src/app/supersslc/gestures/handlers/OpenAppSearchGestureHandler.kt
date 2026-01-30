package app.supersslc.gestures.handlers

import android.content.Context
import app.supersslc.LawnchairLauncher

class OpenAppSearchGestureHandler(context: Context) : OpenAppDrawerGestureHandler(context) {

    override suspend fun onTrigger(launcher: LawnchairLauncher) {
        super.onTrigger(launcher)
        launcher.appsView.searchUiManager.editText?.showKeyboard()
    }
}
