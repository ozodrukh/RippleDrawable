package codetail.graphics.drawables;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public abstract class LollipopDrawable extends Drawable {

    public void inflate(Resources r, XmlPullParser parser, AttributeSet attrs, Resources.Theme theme) throws XmlPullParserException, IOException {
    }

    public boolean canApplyTheme() {
        return false;
    }

    public void applyTheme(Resources.Theme t) {

    }

    public void setTint(int tint) {

    }

    public void setTintList(ColorStateList tint) {

    }

    public void setTintMode(PorterDuff.Mode tintMode) {

    }
}
