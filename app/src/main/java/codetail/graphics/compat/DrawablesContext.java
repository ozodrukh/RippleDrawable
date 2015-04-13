package codetail.graphics.compat;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;

public class DrawablesContext extends ContextWrapper{

    ResourcesCompat mWrapped;

    public DrawablesContext(Context base) {
        super(base);
        mWrapped = new ResourcesCompat(base);
    }

    public Resources getBaseResources() {
        return super.getResources();
    }

    @Override
    public ResourcesCompat getResources() {
        return mWrapped;
    }
}
