package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Null;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Controller
@RequestMapping("/meals")
public class JspMealController {

    @Autowired
    private MealService mealService;

    @Autowired
    private UserService userService;


    @GetMapping()
    public String getMeals(Model model,HttpServletRequest request) {

        int userId = SecurityUtil.authUserId();
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");


        LocalDate startD = null;
        LocalDate endD = null;
        LocalTime startTimeT = null;
        LocalTime endTimeT = null;
        if (!(startDate== null || startDate.isBlank() || startDate.isEmpty()) ) startD = LocalDate.parse(startDate);
        if (!(endDate == null || endDate.isBlank() || endDate.isEmpty()) )endD = LocalDate.parse(endDate);
        if (!(startTime == null || startTime.isBlank() || startTime.isEmpty()) )startTimeT = LocalTime.parse(startTime);
        if (!(endTime==null || endTime.isBlank() || endTime.isEmpty()) )endTimeT = LocalTime.parse(endTime);

        int caloriesPerDay = userService.get(userId).getCaloriesPerDay();

        List<Meal> betweenDates = mealService.getBetweenDates(startD,endD,userId);
        List<MealTo> betweenDatesAndTime = MealsUtil.getFilteredTos(betweenDates,caloriesPerDay,startTimeT, endTimeT);


//        model.addAttribute("meals", MealsUtil.getTos(mealService.getAll(userId),
//                userService.get(userId).getCaloriesPerDay()));
        model.addAttribute("meals",betweenDatesAndTime);
        return "meals";
    }


    @GetMapping("/update/{mealId}")
    public String updateMealForm(Model model, @PathVariable("mealId") Integer mealId) {

        int userId = SecurityUtil.authUserId();
        model.addAttribute("userId", userId);
        model.addAttribute("action", "update");
        model.addAttribute("meal", mealService.get(mealId,userId));
        return "mealForm";
    }


    @PostMapping("/update")
    public String updateMeal(Model model,
                             @ModelAttribute("id") String mealId,
                             @ModelAttribute("dateTime") String dateTime,
                             @ModelAttribute("description") String description,
                             @ModelAttribute("calories") Integer calories


    ) {

        int userId = SecurityUtil.authUserId();
        Integer mealIdInteger=null;
        if (!(mealId.isEmpty() || mealId.isBlank()) ) mealIdInteger = Integer.parseInt(mealId);
        Meal meal = new Meal(mealIdInteger, LocalDateTime.parse(dateTime), description,calories);
        if (meal.isNew()) mealService.create(meal, userId);
        else mealService.update(meal, userId);
        model.asMap().clear();
        return "redirect:/meals";
    }

    @GetMapping("/delete/{mealId}")
    public String deleteMeal(Model model, @PathVariable("mealId") Integer mealId) {

        int userId = SecurityUtil.authUserId();
        mealService.delete(mealId,userId);
        return "redirect:/meals";
    }


    @GetMapping("/create")
    public String createMealForm(Model model) {
        int userId = SecurityUtil.authUserId();
        model.addAttribute("userId", userId);
        model.addAttribute("meal", new Meal());
        model.addAttribute("action", "create");
        return "mealForm";
    }

//    @PostMapping("/create")
//    public String createMeal(Model model) {
//        int userId = SecurityUtil.authUserId();
//        mealService.create(meal,userId);
//        model.asMap().clear();
//        return "redirect:/meals";
//    }

}
