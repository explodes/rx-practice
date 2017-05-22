package quiz.explod.io.rxpractice.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.explod.querydb.db.Migration;
import io.explod.querydb.db.QueryDb;

public class FoodDatabase extends QueryDb {

    private static final String NAME = null; // in-memory

    private static final int VERSION = VERSION_INITIAL;


    public FoodDatabase(@NonNull Context context) {
        super(context, NAME, null, VERSION);
    }

    @Nullable
    @Override
    protected Migration getMigration(int version) {
        switch (version) {
            case VERSION_INITIAL:
                return new Migration() {
                    @Override
                    public void execute(@NonNull SQLiteDatabase db) {
                        db.execSQL("CREATE TABLE foods (_id integer primary key, name text not null, rating integer not null)");
                    }
                };
        }
        return null;
    }


}
