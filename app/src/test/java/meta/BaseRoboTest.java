package meta;

import android.content.Context;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.util.Scheduler;

import quiz.explod.io.rxpractice.App;
import quiz.explod.io.rxpractice.BuildConfig;

import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, application = App.class)
public abstract class BaseRoboTest {

    @Before
    public void setUpDexcache() {
        System.setProperty("dexmaker.dexcache", RuntimeEnvironment.application.getCacheDir().getPath());
    }

    protected static void waitForMainLooper() {
        Context context = RuntimeEnvironment.application;

        Scheduler scheduler = shadowOf(context.getMainLooper()).getScheduler();
        while (scheduler.advanceToLastPostedRunnable()) ;
    }
}
