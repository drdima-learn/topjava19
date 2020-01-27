package ru.javawebinar.topjava.repository.jdbc.postgres;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.repository.jdbc.JdbcMealRepositoryGeneral;

@Profile(Profiles.POSTGRES_DB)
@Repository
public class PostgresJdbcMealRepository extends JdbcMealRepositoryGeneral {

    public PostgresJdbcMealRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }
}
