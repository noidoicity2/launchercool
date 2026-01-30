package app.supersslc.compat

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import app.supersslc.compatlib.ActivityManagerCompat
import app.supersslc.compatlib.ActivityOptionsCompat
import app.supersslc.compatlib.QuickstepCompatFactory
import app.supersslc.compatlib.RemoteTransitionCompat
import app.supersslc.compatlib.eleven.QuickstepCompatFactoryVR
import app.supersslc.compatlib.fifteen.QuickstepCompatFactoryVV
import app.supersslc.compatlib.fourteen.QuickstepCompatFactoryVU
import app.supersslc.compatlib.sixteen.QuickstepCompatFactoryVBaklava
import app.supersslc.compatlib.ten.QuickstepCompatFactoryVQ
import app.supersslc.compatlib.thirteen.QuickstepCompatFactoryVT
import app.supersslc.compatlib.twelve.QuickstepCompatFactoryVS

object LawnchairQuickstepCompat {

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.Q)
    @JvmField
    val ATLEAST_Q: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.R)
    @JvmField
    val ATLEAST_R: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.R

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
    @JvmField
    val ATLEAST_S: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.TIRAMISU)
    @JvmField
    val ATLEAST_T: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    @JvmField
    val ATLEAST_U: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    @JvmField
    val ATLEAST_V: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM

    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.BAKLAVA)
    @JvmField
    val ATLEAST_BAKLAVA: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.BAKLAVA

    @JvmStatic
    val factory: QuickstepCompatFactory = when {
        ATLEAST_BAKLAVA -> QuickstepCompatFactoryVBaklava()
        ATLEAST_V -> QuickstepCompatFactoryVV()
        ATLEAST_U -> QuickstepCompatFactoryVU()
        ATLEAST_T -> QuickstepCompatFactoryVT()
        ATLEAST_S -> QuickstepCompatFactoryVS()
        ATLEAST_R -> QuickstepCompatFactoryVR()
        ATLEAST_Q -> QuickstepCompatFactoryVQ()
        else -> error("Unsupported SDK version")
    }

    @JvmStatic
    val activityManagerCompat: ActivityManagerCompat = factory.activityManagerCompat

    @JvmStatic
    val activityOptionsCompat: ActivityOptionsCompat = factory.activityOptionsCompat

    @JvmStatic
    val remoteTransitionCompat: RemoteTransitionCompat = factory.remoteTransitionCompat
}
