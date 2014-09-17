RippleDrawable
=====

A port of Ripple Effect from Android L to non L devices




Usage
-----

```java
    // Create circular ripple effect to view
    Button view = new Button(MainActivity.this);
    RippleDrawable.createRipple(view, getColor(R.color.material_blue_600));
    view.setOnClickListener(new OnClickListener(){
      
      @Override
      public void onClick(View v){
        Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
      }
      
    });
    
```



License
--------

    The MIT License (MIT)

    Copyright (c) 2014 Abdullaev Ozodrukh
    
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

