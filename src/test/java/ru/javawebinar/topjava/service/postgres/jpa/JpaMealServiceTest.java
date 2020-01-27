package ru.javawebinar.topjava.service.postgres.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.MealServiceTest;

@ActiveProfiles({Profiles.POSTGRES_DB,Profiles.JPA})
public class JpaMealServiceTest extends MealServiceTest {
}