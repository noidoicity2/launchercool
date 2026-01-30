package app.supersslc.compatlib.ten;

import android.window.RemoteTransition;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import app.supersslc.compatlib.ActivityManagerCompat;
import app.supersslc.compatlib.ActivityOptionsCompat;
import app.supersslc.compatlib.QuickstepCompatFactory;
import app.supersslc.compatlib.RemoteTransitionCompat;

@RequiresApi(29)
public class QuickstepCompatFactoryVQ implements QuickstepCompatFactory {
    protected final String TAG = getClass().getCanonicalName();

    @NonNull
    @Override
    public ActivityManagerCompat getActivityManagerCompat() {
        return new ActivityManagerCompatVQ();
    }

    @NonNull
    @Override
    public ActivityOptionsCompat getActivityOptionsCompat() {
        return new ActivityOptionsCompatVQ();
    }

    @NonNull
    @Override
    public RemoteTransitionCompat getRemoteTransitionCompat() {
        return RemoteTransition::new;
    }
}
