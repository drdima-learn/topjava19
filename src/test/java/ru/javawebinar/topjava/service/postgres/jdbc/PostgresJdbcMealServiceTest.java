package ru.javawebinar.topjava.service.postgres.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.MealServiceTest;

@ActiveProfiles({Profiles.POSTGRES_DB,Profiles.JDBC})
public class PostgresJdbcMealServiceTest extends MealServiceTest {
}
