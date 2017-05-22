package quiz.explod.io.rxpractice.rx;

import android.database.Cursor;
import android.support.annotation.NonNull;

import com.fernandocejas.arrow.optional.Optional;

import io.explod.querydb.util.CursorUtils;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import quiz.explod.io.rxpractice.data.Food;
import quiz.explod.io.rxpractice.data.FoodDatabase;

public class QueryFoods {

    private static final String TABLE = "foods";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_RATING = "rating";

    @NonNull
    private final FoodDatabase mDb;

    public QueryFoods(@NonNull FoodDatabase db) {
        mDb = db;
    }

    /**
     * Given an ID, retrieve the food from the database. If the food with the given ID does not exist,
     * and empty Optional is emitted instead.
     *
     * Tips:
     * Create a single with your own code with "Single.fromCallable"
     * Use mDb.select().table(TABLE).columns(COLUMN_NAME, COLUMN_RATING).byId(id).execute() to get a cursor
     * Use "CursorUtils" to extract values.
     * Use new Food(...) to create a new food.
     *
     * @param id ID of the food to fetch
     * @return A Single, executing on the IO scheduler, that will emit an Optional Food item.
     */
    @NonNull
    public Single<Optional<Food>> getFoodById(long id) {
        return Single.fromCallable(
                () -> {
                    Cursor c = null;
                    try {
                        c = mDb.select().table(TABLE).columns(COLUMN_NAME, COLUMN_RATING).byId(id).execute();
                        if (!c.moveToFirst()) {
                            return Optional.<Food>absent();
                        } else {
                            String name = CursorUtils.getString(c, COLUMN_NAME);
                            int rating = CursorUtils.getInt(c, COLUMN_RATING);
                            return Optional.of(new Food(id, name, rating));
                        }
                    } finally {
                        if (c != null) {
                            c.close();
                        }
                    }
                })
                .observeOn(Schedulers.io());
    }

    /**
     * Retrieve all food items from the database. Each row should be emitted as a single onNext.
     *
     * Be sure to close the cursor when you're done!
     *
     * Tips:
     * Use Observable.create(s -> { your code here }); to make an observable.
     * Use s.onNext(food) to emit an item
     * Use s.onError(ex) to emit an exception
     * Use s.onComplete() when you are finished
     *
     * @return An Observable, executing on the IO scheduler, that will emit one onNext for each item
     * in the database
     */
    @NonNull
    public Observable<Food> getAllFood() {
        return Observable.<Food>create(
                s -> {
                    Cursor c = null;
                    try {
                        c = mDb.select().table(TABLE).columns(COLUMN_ID, COLUMN_NAME, COLUMN_RATING).execute();
                        while (c.moveToNext()) {
                            long id = CursorUtils.getId(c);
                            String name = CursorUtils.getString(c, COLUMN_NAME);
                            int rating = CursorUtils.getInt(c, COLUMN_RATING);
                            s.onNext(new Food(id, name, rating));
                        }
                        s.onComplete();
                    } catch (Exception ex) {
                        s.onError(new Exception("Unable to query foods", ex));
                    } finally {
                        if (c != null) {
                            c.close();
                        }
                    }
                })
                .observeOn(Schedulers.io());
    }
}
