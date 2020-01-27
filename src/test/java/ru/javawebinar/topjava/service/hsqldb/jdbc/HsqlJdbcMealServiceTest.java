package ru.javawebinar.topjava.service.hsqldb.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.MealServiceTest;

@ActiveProfiles({Profiles.HSQL_DB,Profiles.JDBC})
public class HsqlJdbcMealServiceTest extends MealServiceTest {
}
