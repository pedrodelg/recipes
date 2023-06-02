package com.mendix.recipes.operation;

import com.mendix.recipes.error.RecipesException;
import com.mendix.recipes.model.dto.CategoriesDTO;
import com.mendix.recipes.model.dto.RecipeResponseDTO;
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
    public RecipesDTO getRecipes(String category, String name) {

        RecipesDTO recipesDTO = new RecipesDTO();
        recipesDTO.setRecipesList(
                category == null && name == null ?
                        recipesRepository.getRecipesDTO().getRecipesList() :
                        recipesRepository.getRecipesDTO().getRecipesList().stream()
                                .filter(categoryStream -> category == null ||
                                        categoryStream.getRecipeml().getRecipe().getHead().getCategories().getCat().stream()
                                                .anyMatch(cat -> cat.toLowerCase().contains(category.toLowerCase())))
                                .filter(categoryStream2 -> name == null ||
                                        categoryStream2.getRecipeml().getRecipe().getHead().getTitle().toLowerCase().contains(name.toLowerCase()))
                                .collect(Collectors.toList())
        );

        return recipesDTO;
    }

    @Override
    public RecipeResponseDTO addNewRecipe(Recipeml newRecipe) {

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

            return new RecipeResponseDTO("Recipe added successfully", newRecipe);
        }else{
            throw new RecipesException("The recipe: '" +newRecipeTitle +"' already exists");
        }

    }

    @Override
    public CategoriesDTO getCategories() {

        return new CategoriesDTO(recipesRepository.getRecipesDTO().getRecipesList()
                .stream().map(recipeComplete -> recipeComplete.getRecipeml())
                .map(Recipeml::getRecipe)
                .map(Recipe::getHead)
                .map(Head::getCategories)
                .flatMap(categories -> categories.getCat().stream()).collect(Collectors.toList()));

    }
}
