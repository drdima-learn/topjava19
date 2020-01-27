package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static ru.javawebinar.topjava.util.DateTimeUtil.getEndExclusive;
import static ru.javawebinar.topjava.util.DateTimeUtil.getEndInclusive;
import static ru.javawebinar.topjava.util.DateTimeUtil.getStartInclusive;


@Repository
public class DataJpaMealRepository implements MealRepository {

    @Autowired
    private CrudMealRepository crudRepository;


    @Autowired
    private CrudUserRepository crudUserRepository;

    @Override
    public Meal save(Meal meal, int userId) {
        meal.setUser(crudUserRepository.getOne(userId));
        if (!meal.isNew() && (get(meal.getId(),userId)==null)){
            return null;
        }
        return crudRepository.save(meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudRepository.deleteByIdAndUserId(id,userId)>0;

    }

    @Override
    public Meal get(int id, int userId) {
        return crudRepository.findByIdAndUserId(id,userId);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return crudRepository.findAllByUserIdOrderByDateTimeDesc(userId);
    }

    @Override
    public List<Meal> getBetweenInclusive(LocalDate startDate, LocalDate endDate, int userId) {


        //return crudRepository.getBetweenInclusive(startDate.atStartOfDay(),endDate.atTime(LocalTime.MAX),userId);
        return crudRepository.findAllByUserIdAndDateTimeBetweenOrderByDateTimeDesc(userId, getStartInclusive(startDate),getEndInclusive(endDate));
        //return null;
    }

    @Override
    public Meal getWithUser(Integer id, Integer userId) {
        return crudRepository.getWithUser(id,userId);
    }
}
