package quiz.explod.io.rxpractice.rx;

import com.fernandocejas.arrow.optional.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.robolectric.RuntimeEnvironment;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import meta.BaseRoboTest;
import meta.rx.ImmediateSchedulerRule;
import quiz.explod.io.rxpractice.data.Food;
import quiz.explod.io.rxpractice.data.FoodDatabase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class QueryFoodsTest extends BaseRoboTest {

    @Rule
    public ImmediateSchedulerRule mImmediateSchedulerRule = new ImmediateSchedulerRule();

    QueryFoods foods;

    FoodDatabase db;

    @Before
    public void setUp() {
        db = new FoodDatabase(RuntimeEnvironment.application);
        foods = new QueryFoods(db);
    }

    @After
    public void tearDown() {
        db.close();
    }

    @Test
    public void getFoodById_withExistingId_shouldReturnSingleOfThatFood() throws Exception {
        long hotDogId = db.insert().table("foods").value("name", "Hotdog").value("rating", 8).execute();

        Optional<Food> result = foods.getFoodById(hotDogId)
                .blockingGet();

        assertNotNull(result);
        assertTrue(result.isPresent());
        Food food = result.get();
        assertEquals(new Food(hotDogId, "Hotdog", 8), food);
    }

    @Test
    public void getAllFood() throws Exception {
        long hotDogId = db.insert().table("foods").value("name", "Hotdog").value("rating", 8).execute();
        long pizzaId = db.insert().table("foods").value("name", "Pizza").value("rating", 9).execute();
        long saladId = db.insert().table("foods").value("name", "Salad").value("rating", 2).execute();

        Map<Long, Food> allFoods = foods.getAllFood()
                .collect((Callable<HashMap<Long, Food>>) HashMap::new, (map, item) -> map.put(item.id, item))
                .blockingGet();

        assertNotNull(allFoods);
        assertEquals(3, allFoods.size());
        assertEquals(new Food(hotDogId, "Hotdog", 8), allFoods.get(hotDogId));
        assertEquals(new Food(pizzaId, "Pizza", 9), allFoods.get(pizzaId));
        assertEquals(new Food(saladId, "Salad", 2), allFoods.get(saladId));

    }

}