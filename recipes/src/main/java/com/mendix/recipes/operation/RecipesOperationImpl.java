package com.mendix.recipes.operation;

import com.mendix.recipes.error.RecipesException;
import com.mendix.recipes.model.dto.RecipesDTO;
import com.mendix.recipes.model.rest.Head;
import com.mendix.recipes.model.rest.Recipe;
import com.mendix.recipes.model.rest.RecipeComplete;
import com.mendix.recipes.model.rest.Recipeml;
import com.mendix.recipes.repository.RecipesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RecipesOperationImpl implements RecipesOperation{

    private final RecipesRepository recipesRepository;

    @Override
    public RecipesDTO getRecipes(String category) {

        RecipesDTO recipesDTO = new RecipesDTO();
        recipesDTO.setRecipesList(
                category == null ?
                        recipesRepository.getRecipesDTO().getRecipesList() :
                        recipesRepository.getRecipesDTO().getRecipesList().stream()
                                .filter(categoryStream -> categoryStream.getRecipeml().getRecipe().getHead().getCategories().getCat().contains(category))
                                .collect(Collectors.toList())
        );

        return recipesDTO;
    }

    @Override
    public String addNewRecipe(Recipeml newRecipe) {

        String newRecipeTitle = newRecipe.getRecipe().getHead().getTitle();

        Boolean ifRepeatedRecipe =
         recipesRepository.getRecipesDTO().getRecipesList().stream()
                .map(RecipeComplete::getRecipeml)
                .map(Recipeml::getRecipe)
                .map(Recipe::getHead)
                .map(Head::getTitle)
                .anyMatch(title -> title.equals(newRecipeTitle));

        if (!ifRepeatedRecipe){
            List<RecipeComplete> updateRecipesList = new ArrayList<>(recipesRepository.getRecipesDTO().getRecipesList());
            RecipeComplete addRecipeComplete = new RecipeComplete();

            addRecipeComplete.setRecipeml(newRecipe);
            updateRecipesList.add(addRecipeComplete);

            recipesRepository.getRecipesDTO().setRecipesList(updateRecipesList);

            return "Recipe added successfully";
        }else{
            throw new RecipesException("The recipe: '" +newRecipeTitle +"' already exists");
        }

    }
}
