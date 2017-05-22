package quiz.explod.io.rxpractice.rx;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import meta.rx.ImmediateSchedulerRule;

import static org.junit.Assert.assertArrayEquals;

public class MathematicalMappingTest {

    @Rule
    public ImmediateSchedulerRule mImmediateSchedulerRule = new ImmediateSchedulerRule();

    MathematicalMapping map;

    @Before
    public void setUp() {
        map = new MathematicalMapping();
    }

    @Test
    public void performCrazyTransformations() throws Exception {
        Observable<Long> source = Observable.fromArray(6L, 7L, 8L, 9L);
        List<Long> expected = Arrays.asList(-21L, -41L, -61L, -81L);

        List<Long> results = map.performCrazyTransformations(source)
                .collect((Callable<List<Long>>) ArrayList::new, List::add)
                .blockingGet();

        assertArrayEquals(expected.toArray(new Long[0]), results.toArray(new Long[0]));
    }

}