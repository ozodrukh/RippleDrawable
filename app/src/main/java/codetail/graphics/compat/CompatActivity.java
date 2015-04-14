package codetail.graphics.compat;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import codetail.graphics.drawables.DrawablesCompat;
import codetail.graphics.drawables.TouchTracker;
import dreamers.graphics.R;

/**
 * Base class for activities that uses custom drawables features
 */
public class CompatActivity extends ActionBarActivity{
    private static final int ATTR_BACKGROUND = 0;
    private static final int ATTR_IMG_SRC = 1;
    private static final int ATTR_IS_IN_SCROLL = 2;

    private static final int[] COMPAT_ATTRS = {
            R.attr.backgroundCompat,
            R.attr.srcCompat,
            R.attr.isInScrollContainer,
    };

    private DrawablesContext mContext;
    private final TypedValue mValue = new TypedValue();

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(mContext = new DrawablesContext(newBase));
    }

    @Override
    public ResourcesCompat getResources() {
        return mContext.getResources();
    }

    public Drawable getDrawableCompat(int res){
        return DrawablesCompat.getDrawable(mContext.getBaseResources(), res, getTheme());
    }

    @Override
    protected void onApplyThemeResource(Resources.Theme theme, int resid, boolean first) {
        super.onApplyThemeResource(theme, resid, first);

        /**
         *  This is important moment, if do not set the Theme,
         *  attributes you set in drawable will crash your app
         *
         *  <pre>
         *      <ripple
         *           xmlns:android="http://schemas.android.com/apk/res/android"
         *            android:color="?colorControlHighlight" />
         *  </pre>
         */
        mContext.getResources().setTheme(theme);
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        View view = super.onCreateView(parent, name, context, attrs);

        /**
         * Here we are going to work around private <code>loadDrawable()</code> api
         * Because we are not able to override it, we need to create custom attrs for
         * background, src and etc, here we will extract and set for views
         */

        TypedArray a = context.obtainStyledAttributes(attrs, COMPAT_ATTRS);

        try {
            if(a.getValue(ATTR_BACKGROUND, mValue)){
                view.setBackgroundResource(mValue.resourceId);

                TouchTracker tracker = new TouchTracker();
                tracker.setInsideScrollContainer(a.getBoolean(ATTR_IS_IN_SCROLL, false));
            }

            if(view instanceof ImageView && a.getValue(ATTR_IMG_SRC, mValue)){
                ((ImageView) view).setImageResource(mValue.resourceId);
            }

        }finally {
            a.recycle();
        }

        return view;
    }
}
