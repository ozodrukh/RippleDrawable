package dreamers.sample;

import android.app.Application;
import android.content.Context;

import com.telly.mrvector.MrVector;

import codetail.graphics.compat.DrawablesContext;

public class MyApp extends Application {

    static {
        MrVector.register(R.drawable.category_movies);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(new DrawablesContext(base));
    }

}
