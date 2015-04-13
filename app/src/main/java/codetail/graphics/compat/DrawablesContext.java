package codetail.graphics.compat;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;

public class DrawablesContext extends ContextWrapper{

    ResourcesCompat mWrapped;

    public DrawablesContext(Context base) {
        super(base);
        mWrapped = new ResourcesCompat(base);

        try{
            mWrapped.setTheme(base.getTheme());
        }catch (NullPointerException ignored){
            //todo what to on exception? how to set theme, cause it's really important
        }
    }

    public Resources getBaseResources() {
        return super.getResources();
    }

    @Override
    public ResourcesCompat getResources() {
        return mWrapped;
    }
}
