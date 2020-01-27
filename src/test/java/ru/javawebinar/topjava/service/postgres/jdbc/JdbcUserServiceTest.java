package ru.javawebinar.topjava.service.postgres.jdbc;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.UserServiceTest;

@ActiveProfiles({Profiles.POSTGRES_DB,Profiles.JDBC})
public class JdbcUserServiceTest extends UserServiceTest {
}