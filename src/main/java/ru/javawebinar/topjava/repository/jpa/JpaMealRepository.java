package ru.javawebinar.topjava.repository.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = false)
public class JpaMealRepository implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {


        //meal.setUser(userService.get(userId));
        meal.setUser(em.getReference(User.class,userId));
        if (meal.isNew()){
            em.persist(meal);
            return meal;
        }
        else {
            //@NamedQuery(name = Meal.UPDATE, query = "UPDATE Meal m SET m.dateTime = :dateTime, m.calories = :calories ,
            // m.description = :description WHERE m.id=:id AND m.user.id = :userId  "),
            boolean updateStatus=  em.createNamedQuery(Meal.UPDATE)
                    .setParameter("dateTime", meal.getDateTime())
                    .setParameter("calories", meal.getCalories())
                    .setParameter("description", meal.getDescription())
                    .setParameter("id", meal.getId())
                    .setParameter("userId", userId)
                    .executeUpdate()!=0;
            if (updateStatus) return get(meal.getId(),userId);
            else {
                throw new NotFoundException("meal: " + meal + " userId: "+ userId + " one of them not found");

            }
        }
    }

    @Override
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(Meal.DELETE)
                .setParameter("id", id)
                .setParameter("user_id", userId)
                .executeUpdate() != 0;

    }

    @Override
    @Transactional
    public Meal get(int id, int userId) {
        try {
            return em.createNamedQuery(Meal.FIND_BY_ID, Meal.class)
                    .setParameter("id", id)
                    .setParameter("user_id", userId)
                    .getSingleResult();
        }
        catch (NoResultException e){
            throw new NotFoundException("mealId: " + id + " userId: " + userId + " one of them not found");
        }



    }

    @Override
    public List<Meal> getAll(int userId) {
        return em.createNamedQuery(Meal.ALL_SORTED, Meal.class)
                .setParameter("user_id", userId)
                .getResultList();
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return em.createNamedQuery(Meal.BETWEEN, Meal.class)
                .setParameter("userId", userId)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }
}