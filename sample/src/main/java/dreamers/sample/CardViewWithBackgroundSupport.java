package dreamers.sample;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

public class CardViewWithBackgroundSupport extends CardView {

    private Drawable mBackground;

    public CardViewWithBackgroundSupport(Context context) {
        this(context, null);
    }

    public CardViewWithBackgroundSupport(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CardViewWithBackgroundSupport(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CardViewWithBackgroundSupport);
        setBackground(array.getDrawable(R.styleable.CardViewWithBackgroundSupport_android_background));
        array.recycle();

    }

    @Override
    protected boolean verifyDrawable(Drawable who) {
        return who == mBackground || super.verifyDrawable(who);
    }

    @Override
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        if (mBackground != null) {
            mBackground.jumpToCurrentState();
        }
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (mBackground != null) {
            mBackground.setState(getDrawableState());
        }
    }

    @Override
    public void setBackground(Drawable background) {
        if (mBackground != null) {
            mBackground.setCallback(null);
            unscheduleDrawable(mBackground);
        }

        mBackground = background;

        if (background != null) {
            background.setLevel(0);
            if (background.isStateful()) {
                background.setState(getDrawableState());
            }
            background.setVisible(getVisibility() == VISIBLE, true);
            background.setCallback(this);
        }

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mBackground != null) {
            mBackground.setBounds(0, 0, getWidth(), getHeight());
            mBackground.draw(canvas);
        }
    }
}
