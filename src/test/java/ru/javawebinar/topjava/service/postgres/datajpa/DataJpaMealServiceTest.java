package ru.javawebinar.topjava.service.postgres.datajpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.MealServiceTest;

@ActiveProfiles({Profiles.POSTGRES_DB,Profiles.DATAJPA})
public class DataJpaMealServiceTest extends MealServiceTest {
}
