package com.mendix.recipes.controller;

import com.mendix.recipes.model.dto.RecipeResponseDTO;
import com.mendix.recipes.model.dto.RecipesDTO;
import com.mendix.recipes.model.rest.Recipeml;
import com.mendix.recipes.operation.RecipesOperation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class RecipesController {

    private final RecipesOperation recipesOperation;

    @GetMapping("/getRecipes")
    public ResponseEntity<RecipesDTO> recipesGet(@RequestParam(value = "category", required = false) String category,
                                                 @RequestParam(value = "name", required = false) String name){

        return new ResponseEntity<RecipesDTO>(recipesOperation.getRecipes(category, name), null, HttpStatus.OK);
    }

    @PostMapping("/new-recipe")
    public ResponseEntity<RecipeResponseDTO> addNewRecipe(@Valid @RequestBody Recipeml newRecipe) {

        return new ResponseEntity<RecipeResponseDTO>(recipesOperation.addNewRecipe(newRecipe), null, HttpStatus.CREATED);
    }

    @GetMapping("/getCategories")
    public ResponseEntity<List<String>> recipesGet(){

        return new ResponseEntity<List<String>>(recipesOperation.getCategories(), null, HttpStatus.OK);
    }
}
