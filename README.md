### I'm sorry it was an accident to merge with dev branch, it is unstable version, if you could help how to revert changes, please do Pull Request


*RippleDrawable*
=============
This repository contains post of Android `<ripple>` effect for pre lollipop devices with android 14 + (ICS+)

### Features
1. XML inflating (working on)
2. Ripple supports different shapes
3. Custom drawable loader
4. Define your custom drawable tags

#### Implementation

create this file in your drawables/ or drawables-v14/ folder
```xml
<?xml version="1.0" encoding="utf-8"?>
<ripple
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:color="@color/ripple_material_light">

    <!-- for fab -->
    <item>
        <shape android:shape="oval">
            <solid android:color="@color/accent_material_dark"/>
        </shape>
    </item>

</ripple>

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

