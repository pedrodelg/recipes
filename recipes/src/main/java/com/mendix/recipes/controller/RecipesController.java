package com.mendix.recipes.controller;

import com.mendix.recipes.model.RecipeComplete;
import com.mendix.recipes.operation.RecipesOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class RecipesController {

    private final RecipesOperation recipesOperation;

    @GetMapping("/getRecipes")
    public RecipeComplete recipesGet(){

        return recipesOperation.getAllRecipes();
    }
}
