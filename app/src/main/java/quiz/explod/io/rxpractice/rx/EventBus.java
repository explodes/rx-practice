package quiz.explod.io.rxpractice.rx;

import android.support.annotation.NonNull;

import io.reactivex.Observable;

/**
 * Simple EventBus interface.
 *
 * New subscribers should *NOT* receive new events.
 *
 * @param <T> type of event emitted by this bus
 */
public interface EventBus<T> {

    /**
     * Publish an event to this event bus. All subscribers should receive the event.
     *
     * @param event Event to publish
     */
    void publish(@NonNull T event);

    /**
     * Get the Observable for this event bus.
     *
     * @return the observable for this event bus
     */
    @NonNull
    Observable<T> getObservable();

}
