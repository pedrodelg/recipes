package com.mendix.recipes.operation;

import com.mendix.recipes.model.dto.CategoriesDTO;
import com.mendix.recipes.model.dto.RecipeResponseDTO;
import com.mendix.recipes.model.dto.RecipesDTO;
import com.mendix.recipes.model.rest.Recipeml;

public interface RecipesOperation {

    public RecipesDTO getRecipes(String category, String name);

    public RecipeResponseDTO addNewRecipe(Recipeml newRecipes);

    public CategoriesDTO getCategories();
}
