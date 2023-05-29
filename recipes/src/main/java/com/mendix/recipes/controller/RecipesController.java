package com.mendix.recipes.controller;

import com.mendix.recipes.model.dto.RecipesDTO;
import com.mendix.recipes.operation.RecipesOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class RecipesController {

    private final RecipesOperation recipesOperation;

    @GetMapping("/getRecipes")
    public ResponseEntity<RecipesDTO> recipesGet(@RequestParam String category){

        return new ResponseEntity<RecipesDTO>(recipesOperation.getAllRecipes(), null, HttpStatus.OK);
    }
}
