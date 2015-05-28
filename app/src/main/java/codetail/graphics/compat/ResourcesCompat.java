package codetail.graphics.compat;

import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;

import codetail.graphics.drawables.DrawablesCompat;

public class ResourcesCompat extends Resources {

    Theme mTheme;

    public ResourcesCompat(Resources resources) {
        this(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
    }

    public void setTheme(Theme theme) {
        mTheme = theme;
    }

    public Theme getTheme() {
        return mTheme;
    }

    /**
     * Create a new Resources object on top of an existing set of assets in an
     * AssetManager.
     *
     * @param assets  Previously created AssetManager.
     * @param metrics Current display metrics to consider when
     *                selecting/computing resource values.
     * @param config  Desired device configuration to consider when
     */
    public ResourcesCompat(AssetManager assets, DisplayMetrics metrics, Configuration config) {
        super(assets, metrics, config);
    }

    @Nullable
    @Override
    public Drawable getDrawable(int id) throws NotFoundException {
        return getDrawable(id, null);
    }

    @Nullable
    @Override
    public Drawable getDrawable(int id, Theme theme) throws NotFoundException {
        return DrawablesCompat.getDrawable(this, id, theme);
    }
}