package app.supersslc.compatlib.fourteen;

import android.window.RemoteTransition;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import app.supersslc.compatlib.ActivityManagerCompat;
import app.supersslc.compatlib.ActivityOptionsCompat;
import app.supersslc.compatlib.RemoteTransitionCompat;
import app.supersslc.compatlib.thirteen.QuickstepCompatFactoryVT;

@RequiresApi(34)
public class QuickstepCompatFactoryVU extends QuickstepCompatFactoryVT {

    @NonNull
    @Override
    public ActivityManagerCompat getActivityManagerCompat() {
        return new ActivityManagerCompatVU();
    }

    @NonNull
    @Override
    public ActivityOptionsCompat getActivityOptionsCompat() {
        return new ActivityOptionsCompatVU();
    }

    @NonNull
    @Override
    public RemoteTransitionCompat getRemoteTransitionCompat() {
        return RemoteTransition::new;
    }
}
