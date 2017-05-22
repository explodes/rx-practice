package quiz.explod.io.rxpractice.rx;

import android.support.annotation.NonNull;

import io.reactivex.Observable;

public class MathematicalMapping {


    /**
     * Transform the source observable by following these steps:
     * - Multiply by 2
     * - Convert to string
     * - Reverse the string
     * - Prepend a "-" to the string
     * - Convert back to a number
     *
     * @param source Source observable to transform
     * @return An Observable that performs work on the computation scheduler
     */
    @NonNull
    public Observable<Long> performCrazyTransformations(@NonNull Observable<Long> source) {
        return null;
    }

}
