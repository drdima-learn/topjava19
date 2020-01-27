package ru.javawebinar.topjava.service.postgres.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.UserServiceTest;

@ActiveProfiles({Profiles.POSTGRES_DB,Profiles.JPA})
public class JpaUserServiceTest extends UserServiceTest {
}