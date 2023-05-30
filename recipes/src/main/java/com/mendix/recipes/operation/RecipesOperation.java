package com.mendix.recipes.operation;

import com.mendix.recipes.model.dto.RecipeResponseDTO;
import com.mendix.recipes.model.dto.RecipesDTO;
import com.mendix.recipes.model.rest.Recipeml;

import java.util.List;

public interface RecipesOperation {

    public RecipesDTO getRecipes(String category);

    public RecipeResponseDTO addNewRecipe(Recipeml newRecipes);

    public List<String> getCategories();
}
