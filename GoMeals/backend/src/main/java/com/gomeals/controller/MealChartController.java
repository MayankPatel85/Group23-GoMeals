package com.gomeals.controller;
import com.gomeals.model.MealChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.gomeals.service.MealChartService;
import com.gomeals.model.MealChartID;

import java.util.List;

@RestController
@RequestMapping("/meal_chart")
@CrossOrigin(origins = "http://localhost:3000")
public class MealChartController {
    @Autowired
    MealChartService mealChartService;
    @GetMapping ("/get")
    public MealChart getMealChart(@RequestBody MealChartID id){
        return mealChartService.getMealChart(id);
    }
    @PostMapping ("/create")
    public String addMealChart(@RequestBody List<MealChart> mealChart){
        return mealChartService.addMealChart(mealChart);
    }
    @PutMapping("/update")
    public MealChart updateMealChart(@RequestBody MealChart mealChart){
        return mealChartService.updateMealChart(mealChart);
    }
    @DeleteMapping("/delete")
    public String deleteMealChart(@RequestBody MealChartID id){
        return mealChartService.deleteMealChart(id);
    }

}
