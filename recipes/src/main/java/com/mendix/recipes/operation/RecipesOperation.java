package com.mendix.recipes.operation;

import com.mendix.recipes.model.dto.RecipesDTO;
import com.mendix.recipes.model.rest.Recipeml;

public interface RecipesOperation {

    public RecipesDTO getRecipes(String category);

    public String addNewRecipe(Recipeml newRecipes);
}
