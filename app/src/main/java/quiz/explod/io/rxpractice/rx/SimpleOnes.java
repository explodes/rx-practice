package quiz.explod.io.rxpractice.rx;

import android.support.annotation.NonNull;

import io.reactivex.Observable;

public class SimpleOnes {

    /**
     * Create an Observable that emits 0L to infinity (ignore overflow, just add one to your counter
     * forever.)
     *
     * This observable will never complete by default, just make it produce 0, 1, 2, .... N forever.
     *
     * Tips:
     * Use Observable.create(s -> {  your code here }) to create an observable
     * Use s.onNext to publish the next item
     *
     * BONUS:
     * Make the observable stop performing work when the observable is no longer subscribed to.
     *
     * @return An observable that emits 0 to "infinity"
     */
    @NonNull
    public Observable<Long> countToInfinity() {
        return Observable.create(s -> {
            long count = -1;
            while (!s.isDisposed()) {
                s.onNext(++count);
            }
        });
    }

    /**
     * Create an observable that merges two source observables.
     *
     * Tips:
     * The Observable class has a static method that will do all of the heavy lifting for you.
     *
     * BONUS: Use only one, succinct, line of code to make this happen
     *
     * @param sourceA The first source
     * @param sourceB The second source
     * @return An observable that emits the contents of both sourceA and sourceB
     */
    @NonNull
    public <T> Observable<T> combine(@NonNull Observable<T> sourceA, @NonNull Observable<T> sourceB) {
        return Observable.merge(sourceA, sourceB);
    }

    /**
     * Create an observable that emits "a", "b", "c" for every item emitted by the trigger observable
     *
     * Tips:
     * Using .flatMap(...) will make this work
     *
     * @param trigger The trigger observable
     * @return An observable that emits "a", "b", and "c" for every item emitted by the trigger
     */
    @NonNull
    public Observable<String> abcEmitter(@NonNull Observable<?> trigger) {
        return trigger.flatMap(x -> Observable.fromArray("a", "b", "c"));
    }

}
