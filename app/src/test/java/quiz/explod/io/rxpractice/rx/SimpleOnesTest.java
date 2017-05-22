package quiz.explod.io.rxpractice.rx;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import meta.rx.ImmediateSchedulerRule;

import static org.junit.Assert.assertArrayEquals;

public class SimpleOnesTest {

    @Rule
    public ImmediateSchedulerRule mImmediateSchedulerRule = new ImmediateSchedulerRule();

    SimpleOnes simples;

    @Before
    public void setUp() {
        simples = new SimpleOnes();
    }

    @Test
    public void countToInfinity() throws Exception {
        List<Long> expected = Arrays.asList(0L, 1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L);

        List<Long> nums = simples.countToInfinity()
                .take(10)
                .collect((Callable<List<Long>>) ArrayList::new, List::add)
                .blockingGet();

        assertArrayEquals(expected.toArray(new Long[0]), nums.toArray(new Long[0]));
    }

    @Test
    public void combine() throws Exception {
        Set<String> expected = new HashSet<>(Arrays.asList("a", "b", "c", "d", "e", "f"));
        Observable<String> a = Observable.just("a", "b", "c");
        Observable<String> b = Observable.just("d", "e", "f");

        Set<String> results = simples.combine(a, b)
                .collect((Callable<Set<String>>) HashSet::new, Set::add)
                .blockingGet();

        assertArrayEquals(expected.toArray(new String[0]), results.toArray(new String[0]));
    }

    @Test
    public void abcEmitter() throws Exception {
        Observable<Object> trigger = Observable.just(1L, 2L);

        List<String> results = simples.abcEmitter(trigger)
                .take(10)
                .collect((Callable<List<String>>) ArrayList::new, List::add)
                .blockingGet();

        assertArrayEquals(new String[]{"a", "b", "c", "a", "b", "c"}, results.toArray(new String[0]));

    }

}