package quiz.explod.io.rxpractice.rx;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashSet;
import java.util.Set;

import io.reactivex.Observable;

public class Subscriptions {

    public interface Callback<T> {
        void callback(@Nullable T result);
    }

    /**
     * Call onComplete when the source has completed. Call onError if an error occurs.
     *
     * @param complete callback to call when the source has completed. If error occurred, it is passed back, otherwise it is null
     */
    public void onComplete(@NonNull Observable<?> source, @NonNull Callback<Throwable> complete) {
    }

    /**
     * Return the set of unique items emitted by source
     *
     * @param source source of items to find unique values of
     * @return Set of unique items emitted by the source observable
     */
    @NonNull
    public <T> Set<T> unique(@NonNull Observable<T> source) throws Exception {
        return null;
    }

}
