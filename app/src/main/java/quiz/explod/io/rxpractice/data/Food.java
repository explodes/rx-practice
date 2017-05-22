package quiz.explod.io.rxpractice.data;

import android.support.annotation.NonNull;

public class Food {

    public final long id;

    @NonNull
    public final String name;

    public final int rating;

    public Food(long id, @NonNull String name, int rating) {
        this.id = id;
        this.name = name;
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Food food = (Food) o;

        if (id != food.id) return false;
        if (rating != food.rating) return false;
        return name.equals(food.name);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + rating;
        return result;
    }
}
