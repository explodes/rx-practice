package quiz.explod.io.rxpractice.rx;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

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
        return source
                .map(x -> x * 2)
                .map(String::valueOf)
                .map(MathematicalMapping::reverseString)
                .map(x -> "-" + x)
                .map(Long::parseLong)
                .observeOn(Schedulers.computation());
    }

    @NonNull
    private static String reverseString(@NonNull String s) {
        StringBuilder out = new StringBuilder(s.length());
        for (int i = s.length() - 1; i >= 0; i--) {
            out.append(s.charAt(i));
        }
        return out.toString();
    }

}
