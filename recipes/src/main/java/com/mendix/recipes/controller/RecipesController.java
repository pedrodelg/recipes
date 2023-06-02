package com.mendix.recipes.controller;

import com.mendix.recipes.model.dto.CategoriesDTO;
import com.mendix.recipes.model.dto.RecipeResponseDTO;
import com.mendix.recipes.model.dto.RecipesDTO;
import com.mendix.recipes.model.rest.Recipeml;
import com.mendix.recipes.operation.RecipesOperation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class RecipesController {

    private final RecipesOperation recipesOperation;

    @GetMapping("/get-recipes")
    public ResponseEntity<RecipesDTO> getRecipes(@RequestParam(value = "category", required = false) String category,
                                                 @RequestParam(value = "name", required = false) String name){

        return new ResponseEntity<RecipesDTO>(recipesOperation.getRecipes(category, name), null, HttpStatus.OK);
    }

    @PostMapping("/add-recipe")
    public ResponseEntity<RecipeResponseDTO> addNewRecipe(@Valid @RequestBody Recipeml newRecipe) {

        return new ResponseEntity<RecipeResponseDTO>(recipesOperation.addNewRecipe(newRecipe), null, HttpStatus.CREATED);
    }

    @GetMapping("/get-categories")
    public ResponseEntity<CategoriesDTO> getRecipes(){

        return new ResponseEntity<CategoriesDTO>(recipesOperation.getCategories(), null, HttpStatus.OK);
    }
}
