package quiz.explod.io.rxpractice.rx;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import meta.rx.ImmediateSchedulerRule;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class EventBusFactoryTest {

    @Rule
    public ImmediateSchedulerRule mImmediateSchedulerRule = new ImmediateSchedulerRule();

    EventBus<String> bus;

    @Before
    public void setUp() {
        bus = EventBusFactory.create();
    }

    @Test
    public void subscribe_doesNotReceiveEvent() throws Exception {
        Disposable disposable = null;
        try {

            Consumer<String> consumer = mock(Consumer.class);

            bus.publish("Hello, world");

            disposable = bus.getObservable()
                    .subscribe(consumer);

            verify(consumer, never()).accept(anyString());

        } finally {
            if (disposable != null) disposable.dispose();
        }
    }

    @Test
    public void subscribe_receivesNextEvent() throws Exception {
        Disposable disposable = null;
        try {

            Consumer<String> consumer = mock(Consumer.class);

            disposable = bus.getObservable()
                    .subscribe(consumer);

            bus.publish("Hello, world");

            verify(consumer).accept(eq("Hello, world"));

        } finally {
            if (disposable != null) disposable.dispose();
        }
    }

    @Test
    public void subscribe_multipleReceivesNextEvent() throws Exception {
        Disposable disposable1 = null;
        Disposable disposable2 = null;
        try {

            Consumer<String> consumer1 = mock(Consumer.class);
            Consumer<String> consumer2 = mock(Consumer.class);

            disposable1 = bus.getObservable()
                    .subscribe(consumer1);

            disposable2 = bus.getObservable()
                    .subscribe(consumer2);

            bus.publish("Hello, world");

            verify(consumer1).accept(eq("Hello, world"));
            verify(consumer2).accept(eq("Hello, world"));

        } finally {
            if (disposable1 != null) disposable1.dispose();
            if (disposable2 != null) disposable2.dispose();
        }
    }

}