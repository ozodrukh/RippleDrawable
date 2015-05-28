package codetail.graphics.drawables;

import android.os.Build;

import static android.os.Build.VERSION_CODES.KITKAT;
import static android.os.Build.VERSION_CODES.LOLLIPOP;

/**
 * Helper class to avoid boilerplate Build version checks
 *
 * @hide
 */
class Android {
    public static int VERSION = Build.VERSION.SDK_INT;

    /**
     * Return whether current {@link #VERSION} is equals Lollipop(21)
     * sdk version
     *
     * @return whether current version is equals Lollipop(21)
     * sdk version
     */
    public static boolean isLollipop() {
        return VERSION >= LOLLIPOP;
    }

    /**
     * Return whether current {@link #VERSION} is equals Kitkat(19)
     * sdk version
     *
     * @return whether current version is equals Kitkat(19)
     * sdk version
     */
    public static boolean isKitkat() {
        return VERSION >= KITKAT;
    }

}
