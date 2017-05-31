package quiz.explod.io.rxpractice.rx;

import android.support.annotation.NonNull;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class EventBusFactory {

    /**
     * Create a new Event bus.
     *
     * @param <T> type of event emitted by the event bus
     * @return
     */
    @NonNull
    public static <T> EventBus<T> create() {
        return new EventBus<T>() {

            Subject<T> bus = PublishSubject.create();

            @Override
            public void publish(@NonNull T event) {
                bus.onNext(event);
            }

            @NonNull
            @Override
            public Observable<T> getObservable() {
                return bus.toFlowable(BackpressureStrategy.BUFFER).toObservable();
            }
        };
    }

}
