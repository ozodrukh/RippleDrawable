package dreamers.sample;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

public class FloatingActionButton extends ImageButton{

    public FloatingActionButton(Context context) {
        this(context, null);
    }

    public FloatingActionButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FloatingActionButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setScaleType(ScaleType.CENTER_INSIDE);
    }

}
