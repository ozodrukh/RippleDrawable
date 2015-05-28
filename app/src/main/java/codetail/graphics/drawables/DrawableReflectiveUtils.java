/*
 * Copyright (C) Telly, Inc. and other contributors.
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
package codetail.graphics.drawables;

import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.support.v4.util.LruCache;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;

import java.lang.reflect.Method;

import static android.graphics.PorterDuff.Mode;

/**
 * Stolen
 *
 * @hide
 */
class DrawableReflectiveUtils {
    private final static String TAG = "DrawableReflectiveUtils";

    private static SimpleArrayMap<String, Method> sCachedMethods = new SimpleArrayMap<>();
    private static final ColorFilterLruCache COLOR_FILTER_CACHE = new ColorFilterLruCache(6);

    final static Class[] INT_ARG = {int.class};

    @SuppressWarnings("unchecked")
    public static <T> T tryInvoke(Object target, String methodName, Class<?>[] argTypes, Object... args) {

        try {
            Method method = sCachedMethods.get(methodName);
            if (method != null) {
                return (T) method.invoke(target, args);
            }

            method = target.getClass().getDeclaredMethod(methodName, argTypes);
            sCachedMethods.put(methodName, method);

            return (T) method.invoke(target, args);
        } catch (Exception pokemon) {
            Log.e(TAG, "Unable to invoke " + methodName + " on " + target, pokemon);
        }

        return null;
    }

    public static PorterDuffColorFilter setColor(PorterDuffColorFilter cf, int color, Mode mode) {
        if (!Android.isLollipop()) {
            // First, lets see if the cache already contains the color filter
            PorterDuffColorFilter filter = COLOR_FILTER_CACHE.get(color, mode);

            if (filter == null) {
                // Cache miss, so create a color filter and add it to the cache
                filter = new PorterDuffColorFilter(color, mode);
                COLOR_FILTER_CACHE.put(color, mode, filter);
            }

            return filter;
        }

        /**
         * Otherwise invoke native one
         */
        tryInvoke(cf, "setColor", INT_ARG, color);
        return cf;
    }

    private static class ColorFilterLruCache extends LruCache<Integer, PorterDuffColorFilter> {

        public ColorFilterLruCache(int maxSize) {
            super(maxSize);
        }

        PorterDuffColorFilter get(int color, PorterDuff.Mode mode) {
            return get(generateCacheKey(color, mode));
        }

        PorterDuffColorFilter put(int color, PorterDuff.Mode mode, PorterDuffColorFilter filter) {
            return put(generateCacheKey(color, mode), filter);
        }

        private static int generateCacheKey(int color, PorterDuff.Mode mode) {
            int hashCode = 1;
            hashCode = 31 * hashCode + color;
            hashCode = 31 * hashCode + mode.hashCode();
            return hashCode;
        }
    }
}