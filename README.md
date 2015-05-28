
*RippleDrawable*
=============
This repository contains post of Android `<ripple>` effect for pre lollipop devices with android 14 + (ICS+)

(well, since NineOldAndroids is deprecated, this library become 14 + only, furthermore gingerbread devices is not spread now)

### Features
1. XML inflating
2. Ripple supports different shapes
3. Custom drawable loader
4. Define your custom drawable tags

#### Implementation

Create your desirable ripple.xml in `drawable/` folder

```xml
<?xml version="1.0" encoding="utf-8"?>
<ripple
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:color="@color/ripple_material_light">     <!-- ripple color -->

    <!-- for Floating Action Button -->
    <item>
        <shape android:shape="oval">
            <solid android:color="@color/accent_material_dark"/>
        </shape>
    </item>

</ripple>

```

Secondly we need to inflate `RippleDrawable` and intercept `View` touches
see `LollipopDrawablesCompat` and `DrawableHotspotTouch` for inflating and interception sequently. Here is sample:

```java

public class SampleActivity extends AppCompatActivity {

    private FloatingActionButton mActionButton;

      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        
        mActionButton = (FloatingActionButton) findViewById(R.id.fab);
        mActionButton.setBackgroundDrawable(getDrawable2(R.drawable.fab_background));
        mActionButton.setClickable(true);// if we don't set it true, ripple will not be played
        mActionButton.setOnTouchListener(
                new DrawableHotspotTouch((LollipopDrawable) mActionButton.getBackground()));
    }
    
/**
     * {@link #getDrawable(int)} is already taken by Android API
     * and method is final, so we need to give another name :(
     */
    public Drawable getDrawable2(int id){
        return LollipopDrawablesCompat.getDrawable(getResources(), id, getTheme());
    }
}

```

**Thats it!**

#### a little bit more

you can inflate and create your own `Drawable`classes, here is tips & tricks

1 extend your Drawable from `LollipopDrawable`
```java
    public class LayerDrawable extends LollipopDrawable {
```

2 implement your own inflation
```java
    public void inflate(Resources r, XmlPullParser parser, AttributeSet attrs, Resources.Theme theme);
```
3 Register your `LollipopDrawable`
```java
static {   
    LollipopDrawablesCompat.registerDrawable(RippleDrawable.class, "ripple");
}
```

4 inflate it!
```java
    LollipopDrawablesCompat.getDrawable(getResources(), R.drawable.custom_drawable, getTheme());
```

License
--------

    The MIT License (MIT)

    Copyright (c) 2015 Abdullaev Ozodrukh
    
    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:
    
    The above copyright notice and this permission notice shall be included in
    all copies or substantial portions of the Software.
    
    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
    THE SOFTWARE.


