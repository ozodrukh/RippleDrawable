//package dreamers.sample;
//
//import android.graphics.Canvas;
//import android.graphics.ColorFilter;
//import android.graphics.Paint;
//import android.graphics.PixelFormat;
//import android.graphics.PorterDuff;
//import android.graphics.Rect;
//import android.graphics.drawable.Drawable;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewConfiguration;
//import android.view.ViewGroup;
//import android.view.ViewParent;
//
//import com.nineoldandroids.animation.Animator;
//import com.nineoldandroids.animation.AnimatorSet;
//import com.nineoldandroids.animation.ObjectAnimator;
//
//import dreamers.graphics.Circle;
//
//import static android.view.View.OnTouchListener;
//
//
///**
// * @deprecated
// */
//public class RippleDrawable extends Drawable{
//
//    Circle mTouchCircle;
//    Circle mBoundsCircle;
//
//    Paint mPaint;
//
//    boolean mWithHotspotAnimation;
//
//    AnimatorSet mAnimator;
//    ObjectAnimator mTouchAnimator;
//    ObjectAnimator mBoundsAnimator;
//    ObjectAnimator mPaintAnimator;
//
//    Drawable mOriginalBackground;
//    OnTouchListener mCustomTouchImpl;
//
//    int mAlpha;
//
//    public static RippleDrawable createRipple(View v, int color){
//        RippleDrawable ripple = new RippleDrawable();
//        ripple.mWithHotspotAnimation = true;
//        ripple.mOriginalBackground = v.getBackground();
//        ripple.setAlpha(80);
//        ripple.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
//        v.setOnTouchListener(ripple.getTouchTracker());
//        v.setBackground(ripple);
//
//        return ripple;
//    }
//
//    public static RippleDrawable createRippleForScrollContainer(View target, int color){
//        RippleDrawable ripple = createRipple(target, color);
//        ripple.mWithHotspotAnimation = false;
//        return ripple;
//    }
//
//    public RippleDrawable() {
//        mWithHotspotAnimation = false;
//
//        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        mPaint.setStyle(Paint.Style.FILL);
//
//        mTouchCircle = new Circle(mPaint);
//        mBoundsCircle = new Circle(mPaint);
//
//        mTouchAnimator = new ObjectAnimator();
//        mTouchAnimator.setTarget(mTouchCircle);
//        mTouchAnimator.setPropertyName("radius");
//
//        mBoundsAnimator = mTouchAnimator.clone();
//        mBoundsAnimator.setTarget(mBoundsCircle);
//
//        mPaintAnimator = new ObjectAnimator();
//        mPaintAnimator.setTarget(this);
//        mPaintAnimator.setPropertyName("alpha");
//
//        mAnimator = new AnimatorSet();
//        mAnimator.playTogether(mTouchAnimator, mBoundsAnimator, mPaintAnimator);
//        mAnimator.setDuration(250);
//    }
//
//    @Override
//    public void draw(Canvas canvas) {
//        final int saveState = canvas.getSaveCount();
//        canvas.save();
//
//        Rect bounds = getBounds();
//        canvas.clipRect(bounds);
//        canvas.translate(bounds.left, bounds.top);
//
//        if(mOriginalBackground != null) {
//            mOriginalBackground.setBounds(bounds);
//            mOriginalBackground.draw(canvas);
//        }
//
//        mBoundsCircle.onDraw(canvas);
//        mTouchCircle.onDraw(canvas);
//
//        canvas.restoreToCount(saveState);
//    }
//
//    @Override
//    public void setAlpha(int alpha) {
//        if(!mAnimator.isRunning()){
//           mAlpha = alpha;
//        }
//
//        mPaint.setAlpha(alpha);
//        invalidateSelf();
//    }
//
//    public int getAlpha() {
//        return mPaint.getAlpha();
//    }
//
//    @Override
//    public void setColorFilter(ColorFilter cf) {
//        mPaint.setColorFilter(cf);
//    }
//
//    @Override
//    public int getOpacity() {
//        return PixelFormat.RGBA_8888;
//    }
//
//    public OnTouchListener getTouchTracker(){
//        return new FingerTracker();
//    }
//
//    final class FingerTracker implements OnTouchListener{
//
//        @Override
//        public boolean onTouch(final View v, final MotionEvent event) {
//            final int action = event.getAction();
//            final float x = event.getX(), y = event.getY();
//
//            mTouchCircle.setHotspot(x, y);
//            mBoundsCircle.setHotspot(x, y);
//
//            switch (action){
//                case MotionEvent.ACTION_DOWN:
//                    //startRippleEffect();
//
//                    if(isInScrollingContainer(v)){
//
//
//                        v.postDelayed(new PressedEvent(event, v, RippleDrawable.this),
//                                ViewConfiguration.getTapTimeout());
//                    }
//
//                    return true;
//
//                case MotionEvent.ACTION_MOVE:
//
//                    if(mWithHotspotAnimation) {
//                        invalidateSelf();
//                        v.onTouchEvent(event);
//                        return true;
//                    }
//
//                    return false;
//
//                case MotionEvent.ACTION_CANCEL:
//                case MotionEvent.ACTION_UP:
//                    endRippleEffect();
//                    v.onTouchEvent(event);
//                    return true;
//            }
//
//
//            android.graphics.drawable.RippleDrawable
//            return false;
//        }
//    }
//
//    @Override
//    protected boolean onStateChange(int[] stateSet) {
//        final boolean changed = super.onStateChange(stateSet);
//
//        boolean enabled = false;
//        boolean pressed = false;
//        boolean focused = false;
//
//        for (int state : stateSet) {
//            if (state == android.R.attr.state_enabled) {
//                enabled = true;
//            }
//            if (state == android.R.attr.state_focused) {
//                focused = true;
//            }
//            if (state == android.R.attr.state_pressed) {
//                pressed = true;
//            }
//        }
//
//        //setRippleActive(enabled && pressed);
//        //setBackgroundActive(focused || (enabled && pressed));
//
//        return changed;
//    }
//    private boolean isInScrollingContainer(View v) {
//        ViewParent p = v.getParent();
//        while (p != null && p instanceof ViewGroup) {
//            if (((ViewGroup) p).shouldDelayChildPressedState()) {
//                return true;
//            }
//            p = p.getParent();
//        }
//        return false;
//    }
//
//    public void startRippleEffect(){
//        setAlpha(mAlpha);
//
//        Rect bounds = getBounds();
//
//        mTouchAnimator.setFloatValues(0f, bounds.width() / 4);
//        mBoundsAnimator.setFloatValues(0f, bounds.width() * 1.3f);
//        mPaintAnimator.setIntValues(mAlpha);
//
//        mAnimator.start();
//    }
//
//    public void endRippleEffect(){
//        mAnimator.end();
//
//        mTouchAnimator.setFloatValues(getBounds().width() * 1.3f);
//        mBoundsAnimator.setFloatValues(getBounds().width() * 1.3f);
//        mPaintAnimator.setIntValues(0);
//
//        mAnimator.start();
//    }
//
//    private static final class PressedEvent implements Runnable {
//
//        private final MotionEvent event;
//        private View v;
//        private RippleDrawable drawable;
//
//        private PressedEvent(MotionEvent event, View v, RippleDrawable drawable) {
//            this.event = event;
//            this.v = v;
//            this.drawable = drawable;
//        }
//
//        @Override
//        public void run() {
//            v.setPressed(true);
//            v.onTouchEvent(event);
//            drawable.startRippleEffect();
//        }
//    }
//
//    private static class SimpleListener implements Animator.AnimatorListener{
//
//        @Override
//        public void onAnimationStart(Animator animation) {
//
//        }
//
//        @Override
//        public void onAnimationEnd(Animator animation) {
//
//        }
//
//        @Override
//        public void onAnimationCancel(Animator animation) {
//
//        }
//
//        @Override
//        public void onAnimationRepeat(Animator animation) {
//
//        }
//    }
//
//}
