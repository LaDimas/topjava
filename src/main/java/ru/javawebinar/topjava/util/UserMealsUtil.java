package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        List<UserMealWithExceed> userMealWithExceed = new ArrayList<>();
        LocalDateTime date = mealList.get(0).getDateTime();
        List<UserMeal> oneDay = new ArrayList<>();
        int colories = 0;
        int count = mealList.size();
        for (UserMeal userMeal : mealList) {
            count--;
            if (userMeal.getDateTime().getDayOfMonth() == date.getDayOfMonth()){
                colories += userMeal.getCalories();
                oneDay.add(userMeal);
            }
            if (userMeal.getDateTime().getDayOfMonth() != date.getDayOfMonth() || count == 0){
                for (UserMeal dayMeal : oneDay) {
                    if (TimeUtil.isBetween(dayMeal.getDateTime().toLocalTime(), startTime, endTime)){
                        userMealWithExceed.add(new UserMealWithExceed(dayMeal.getDateTime(), dayMeal.getDescription(), dayMeal.getCalories(), colories <= caloriesPerDay));
                    }
                }
                date = userMeal.getDateTime();
                colories = userMeal.getCalories();
                oneDay.clear();
                oneDay.add(userMeal);
            }
        }
        for (UserMealWithExceed menu : userMealWithExceed){
            System.out.println(menu.getDescription() + " " + menu.isExceed());
        }
        return userMealWithExceed;
    }
}
