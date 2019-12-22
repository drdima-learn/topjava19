package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal meal = service.get(FIRST_MEAL_ID, USER_ID);
        assertMatch(meal, MEALS.get(0));
    }



    @Test(expected = NotFoundException.class)
    public void getWithWrongUserId() {
        Meal meal = service.get(FIRST_MEAL_ID, BAD_ID);
        assertMatch(meal, MEALS.get(0));
    }

    @Test
    public void delete() {
        service.delete(FIRST_MEAL_ID, USER_ID);
        assertMatch(service.getAll(USER_ID), MEALS.subList(1, MEALS.size()));
    }


    @Test(expected = NotFoundException.class)
    public void deleteWithWrongUserId() {
        service.delete(FIRST_MEAL_ID, BAD_ID);
        assertMatch(service.getAll(USER_ID), MEALS.subList(1, MEALS.size()));
    }



    @Test
    public void getAll() {
        assertMatch(service.getAll(USER_ID),MEALS);
    }

    @Test
    public void update() {
        Meal updated = new Meal(FIRST_MEAL_ID + 5, LocalDateTime.of(2019, Month.DECEMBER, 31, 20, 0), "Ужин newyear", 1900);
        service.update(updated, USER_ID);
        assertMatch(service.get(FIRST_MEAL_ID + 5, USER_ID), updated);
    }

    @Test(expected = NotFoundException.class)
    public void updateWithWrongUserId() {
        Meal updated = new Meal(FIRST_MEAL_ID + 5, LocalDateTime.of(2019, Month.DECEMBER, 31, 20, 0), "Ужин newyear", 1900);
        service.update(updated, ADMIN_ID);
        assertMatch(service.get(FIRST_MEAL_ID + 5, USER_ID), updated);
    }

    @Test
    public void create() {
        Meal newMeal = new Meal(null, LocalDateTime.of(2019, Month.DECEMBER, 21, 10, 30), "Zavtrak 21", 15);
        Meal created = service.create(newMeal,USER_ID);
        newMeal.setId(created.getId());


        List<Meal> mealsWithnewMeal = new ArrayList<>(MEALS);
        mealsWithnewMeal.add(newMeal);

        assertMatch(service.getAll(USER_ID), mealsWithnewMeal );
    }
}