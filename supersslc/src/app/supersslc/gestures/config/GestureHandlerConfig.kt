package app.supersslc.gestures.config

import android.content.Context
import app.supersslc.gestures.handlers.GestureHandler
import app.supersslc.gestures.handlers.NoOpGestureHandler
import app.supersslc.gestures.handlers.OpenAppDrawerGestureHandler
import app.supersslc.gestures.handlers.OpenAppGestureHandler
import app.supersslc.gestures.handlers.OpenAppSearchGestureHandler
import app.supersslc.gestures.handlers.OpenAppTarget
import app.supersslc.gestures.handlers.OpenAssistantHandler
import app.supersslc.gestures.handlers.OpenNotificationsHandler
import app.supersslc.gestures.handlers.OpenQuickSettingsHandler
import app.supersslc.gestures.handlers.OpenSearchGestureHandler
import app.supersslc.gestures.handlers.RecentsGestureHandler
import app.supersslc.gestures.handlers.SleepGestureHandler
import com.android.launcher3.R
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
sealed class GestureHandlerConfig {

    abstract fun getLabel(context: Context): String
    abstract fun createHandler(context: Context): GestureHandler

    @Serializable
    sealed class Simple(
        val labelRes: Int,
        @Transient private val creator: (Context) -> GestureHandler = {
            throw IllegalArgumentException("default creator not supported")
        },
    ) : GestureHandlerConfig() {
        override fun getLabel(context: Context) = context.getString(labelRes)
        override fun createHandler(context: Context) = creator(context)
    }

    @Serializable
    @SerialName("noOp")
    data object NoOp : Simple(R.string.gesture_handler_no_op, ::NoOpGestureHandler)

    @Serializable
    @SerialName("sleep")
    data object Sleep : Simple(R.string.gesture_handler_sleep, ::SleepGestureHandler)

    @Serializable
    @SerialName("recents")
    data object Recents : Simple(R.string.gesture_handler_recents, ::RecentsGestureHandler)

    @Serializable
    @SerialName("openNotificationdata")
    data object OpenNotifications : Simple(R.string.gesture_handler_open_notifications, ::OpenNotificationsHandler)

    @Serializable
    @SerialName("openQuickSettings")
    data object OpenQuickSettings :
        Simple(R.string.gesture_handler_open_quick_settings, ::OpenQuickSettingsHandler)

    @Serializable
    @SerialName("openAppDrawer")
    data object OpenAppDrawer : Simple(R.string.gesture_handler_open_app_drawer, ::OpenAppDrawerGestureHandler)

    @Serializable
    @SerialName("openAppSearch")
    data object OpenAppSearch : Simple(R.string.gesture_handler_open_app_search, ::OpenAppSearchGestureHandler)

    @Serializable
    @SerialName("openSearch")
    data object OpenSearch : Simple(R.string.gesture_handler_open_search, ::OpenSearchGestureHandler)

    @Serializable
    @SerialName("openAssistant")
    data object OpenAssistant : Simple(R.string.gesture_handler_open_assistant, ::OpenAssistantHandler)

    @Serializable
    @SerialName("openApp")
    data class OpenApp(val appName: String, val target: OpenAppTarget) : GestureHandlerConfig() {
        override fun getLabel(context: Context) = context.getString(R.string.gesture_handler_open_app_config, appName)
        override fun createHandler(context: Context) = OpenAppGestureHandler(context, target)
    }
}
