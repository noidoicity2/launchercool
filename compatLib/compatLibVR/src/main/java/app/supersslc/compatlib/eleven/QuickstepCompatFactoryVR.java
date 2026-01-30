package app.supersslc.compatlib.eleven;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import app.supersslc.compatlib.ActivityManagerCompat;
import app.supersslc.compatlib.ActivityOptionsCompat;
import app.supersslc.compatlib.ten.QuickstepCompatFactoryVQ;

@RequiresApi(30)
public class QuickstepCompatFactoryVR extends QuickstepCompatFactoryVQ {

    @NonNull
    @Override
    public ActivityManagerCompat getActivityManagerCompat() {
        return new ActivityManagerCompatVR();
    }

    @NonNull
    @Override
    public ActivityOptionsCompat getActivityOptionsCompat() {
        return new ActivityOptionsCompatVR();
    }
}
