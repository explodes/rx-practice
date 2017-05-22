package quiz.explod.io.rxpractice;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class App extends Application {

    @NonNull
    public static App getApp() {
        if (sInstance == null) throw new NullPointerException("App not created");
        return sInstance;
    }

    @Nullable
    private static App sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

}
