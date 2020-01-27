package ru.javawebinar.topjava.repository.jdbc.hsql;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.jdbc.JdbcMealRepositoryGeneral;
import ru.javawebinar.topjava.util.DateTimeUtil;

import java.time.LocalDateTime;

@Profile(Profiles.HSQL_DB)
@Repository
public class HsqlJdbcMealRepository extends JdbcMealRepositoryGeneral {

    public HsqlJdbcMealRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }


    @Override
    protected Object convertDateTime(LocalDateTime ldt) {
        return DateTimeUtil.convertLocalDateTimeToString(ldt);
    }
}
