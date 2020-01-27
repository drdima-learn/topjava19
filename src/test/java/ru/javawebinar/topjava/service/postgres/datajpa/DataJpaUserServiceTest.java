package ru.javawebinar.topjava.service.postgres.datajpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.UserServiceTest;

@ActiveProfiles({Profiles.POSTGRES_DB,Profiles.DATAJPA})
public class DataJpaUserServiceTest extends UserServiceTest {
}
