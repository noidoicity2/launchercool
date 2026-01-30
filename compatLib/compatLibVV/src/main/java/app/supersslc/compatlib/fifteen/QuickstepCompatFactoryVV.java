package app.supersslc.compatlib.fifteen;

import android.window.RemoteTransition;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import app.supersslc.compatlib.ActivityManagerCompat;
import app.supersslc.compatlib.ActivityOptionsCompat;
import app.supersslc.compatlib.RemoteTransitionCompat;
import app.supersslc.compatlib.fourteen.QuickstepCompatFactoryVU;

@RequiresApi(35)
public class QuickstepCompatFactoryVV extends QuickstepCompatFactoryVU {

    @NonNull
    @Override
    public ActivityManagerCompat getActivityManagerCompat() {
        return new ActivityManagerCompatVV();
    }

    @NonNull
    @Override
    public ActivityOptionsCompat getActivityOptionsCompat() {
        return new ActivityOptionsCompatVV();
    }

    @NonNull
    @Override
    public RemoteTransitionCompat getRemoteTransitionCompat() {
        return RemoteTransition::new;
    }
}
