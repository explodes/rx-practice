package quiz.explod.io.rxpractice.rx;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import io.reactivex.Observable;
import io.reactivex.internal.operators.observable.ObservableScan;
import meta.rx.ImmediateSchedulerRule;

import static org.junit.Assert.assertArrayEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SubscriptionsTest {

    @Rule
    public ImmediateSchedulerRule mImmediateSchedulerRule = new ImmediateSchedulerRule();

    Subscriptions subs;

    @Before
    public void setUp() {
        subs = new Subscriptions();
    }

    @Test
    public void onComplete() throws Exception {
        Exception ex = new Exception();
        Subscriptions.Callback<Throwable> callable = mock(Subscriptions.Callback.class);

        subs.onComplete(Observable.error(ex), callable);

        verify(callable).callback(eq(ex));
    }

    @Test
    public void onComplete_withError() throws Exception {
        Subscriptions.Callback<Throwable> callable = mock(Subscriptions.Callback.class);

        subs.onComplete(Observable.empty(), callable);

        verify(callable).callback(isNull(Throwable.class));
    }

    @Test
    public void unique() throws Exception {

        Set<String> result = subs.unique(ObservableScan.fromArray("a", "a", "a", "a", "b"));

        Set<String> expected = new HashSet<>();
        expected.add("a");
        expected.add("b");

        assertArrayEquals(expected.toArray(new String[0]), result.toArray(new String[0]));
    }

    @Test(expected = IllegalStateException.class)
    public void unique_withError() throws Exception {
        subs.unique(Observable.error(new IllegalStateException("error")));
    }

}