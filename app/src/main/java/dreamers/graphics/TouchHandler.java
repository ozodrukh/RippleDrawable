/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dreamers.graphics;

import android.view.MotionEvent;

/**
 * Detects various gestures and events using the supplied {@link android.view.MotionEvent}s.
 * The {@link OnGestureListener} callback will notify users when a particular
 * motion event has occurred. This class should only be used with {@link android.view.MotionEvent}s
 * reported via touch (don't use for trackball events).
 *
 * To use this class:
 * <ul>
 *  <li>Create an instance of the {@code GestureDetector} for your {@link View}
 *  <li>In the {@link View#onTouchEvent(android.view.MotionEvent)} method ensure you call
 *          {@link #onTouchEvent(android.view.MotionEvent)}. The methods defined in your callback
 *          will be executed when the events occur.
 * </ul>
 */
public class TouchHandler {


    public void onTouchEvent(MotionEvent event){
        final int action = event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:{
                break;
            }
        }
    }

}
