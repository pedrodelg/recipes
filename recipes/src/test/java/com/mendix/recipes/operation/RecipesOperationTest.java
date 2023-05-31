package com.mendix.recipes.operation;

import com.mendix.recipes.error.RecipesException;
import com.mendix.recipes.model.dto.RecipeResponseDTO;
import com.mendix.recipes.model.dto.RecipesDTO;
import com.mendix.recipes.model.rest.*;
import com.mendix.recipes.repository.RecipesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class RecipesOperationTest {

    @Mock
    private RecipesRepository recipesRepository;

    @InjectMocks
    private RecipesOperationImpl recipesOperation;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test_get_recipes_with_category() {
        String category = "Chili";

        RecipeComplete recipe1 = RecipeComplete.builder()
                .recipeml(Recipeml.builder()
                        .recipe(Recipe.builder()
                                .head(Head.builder()
                                        .title("30 Minute Chili")
                                        .categories(Categories.builder()
                                                .cat(Arrays.asList("Main dish", "Chili"))
                                                .build())
                                        .yield(6)
                                        .build())
                                .build())
                        .build())
                .build();
        RecipeComplete recipe2 = RecipeComplete.builder()
                .recipeml(Recipeml.builder()
                        .recipe(Recipe.builder()
                                .head(Head.builder()
                                        .title("Spicy Chicken Chili")
                                        .categories(Categories.builder()
                                                .cat(Arrays.asList("Main dish", "Chili"))
                                                .build())
                                        .yield(4)
                                        .build())
                                .build())
                        .build())
                .build();
        List<RecipeComplete> mockRecipesList = Arrays.asList(recipe1, recipe2);

        RecipesDTO mockRecipesDTO = new RecipesDTO();
        mockRecipesDTO.setRecipesList(mockRecipesList);
        when(recipesRepository.getRecipesDTO()).thenReturn(mockRecipesDTO);

        RecipesDTO result = recipesOperation.getRecipes(category, null);

        assertEquals(2, result.getRecipesList().size());
        assertEquals(recipe1, result.getRecipesList().get(0));
        assertEquals(recipe2, result.getRecipesList().get(1));
    }

    @Test
    public void test_get_recipes_without_category() {

        RecipeComplete recipe1 = RecipeComplete.builder()
                .recipeml(Recipeml.builder()
                        .recipe(Recipe.builder()
                                .head(Head.builder()
                                        .title("30 Minute Chili")
                                        .categories(Categories.builder()
                                                .cat(Arrays.asList("Main dish", "Chili"))
                                                .build())
                                        .yield(6)
                                        .build())
                                .build())
                        .build())
                .build();
        RecipeComplete recipe2 = RecipeComplete.builder()
                .recipeml(Recipeml.builder()
                        .recipe(Recipe.builder()
                                .head(Head.builder()
                                        .title("Another Zucchini Dish")
                                        .categories(Categories.builder()
                                                .cat(Arrays.asList("Microwave", "Vegetables"))
                                                .build())
                                        .yield(6)
                                        .build())
                                .build())
                        .build())
                .build();
        List<RecipeComplete> mockRecipesList = Arrays.asList(recipe1, recipe2);


        RecipesDTO mockRecipesDTO = new RecipesDTO();
        mockRecipesDTO.setRecipesList(mockRecipesList);

        when(recipesRepository.getRecipesDTO()).thenReturn(mockRecipesDTO);

        RecipesDTO result = recipesOperation.getRecipes(null,null);

        assertEquals(2, result.getRecipesList().size());
    }

    @Test
    public void test_add_new_recipe_success() {

        Recipeml newRecipe = Recipeml.builder()
                .recipe(Recipe.builder()
                        .head(Head.builder()
                                .title("New Recipe")
                                .build())
                        .build())
                .build();

        RecipeComplete existingRecipe1 = RecipeComplete.builder()
                .recipeml(Recipeml.builder()
                        .recipe(Recipe.builder()
                                .head(Head.builder()
                                        .title("Existing Recipe 1")
                                        .build())
                                .build())
                        .build())
                .build();
        RecipeComplete existingRecipe2 = RecipeComplete.builder()
                .recipeml(Recipeml.builder()
                        .recipe(Recipe.builder()
                                .head(Head.builder()
                                        .title("Existing Recipe 2")
                                        .build())
                                .build())
                        .build())
                .build();
        List<RecipeComplete> mockRecipesList = new ArrayList<>();
        mockRecipesList.add(existingRecipe1);
        mockRecipesList.add(existingRecipe2);

        RecipesDTO mockRecipesDTO = new RecipesDTO();
        mockRecipesDTO.setRecipesList(mockRecipesList);
        when(recipesRepository.getRecipesDTO()).thenReturn(mockRecipesDTO);

        RecipeResponseDTO result = recipesOperation.addNewRecipe(newRecipe);

        assertEquals("Recipe added successfully", result.getMessage());
        assertEquals(newRecipe, result.getRecipeml());

    }

    @Test
    public void test_add_new_recipe_recipe_already_exists() {

        Recipeml newRecipe = Recipeml.builder()
                .recipe(Recipe.builder()
                        .head(Head.builder()
                                .title("Existing Recipe")
                                .build())
                        .build())
                .build();

        RecipeComplete existingRecipe = RecipeComplete.builder()
                .recipeml(Recipeml.builder()
                        .recipe(Recipe.builder()
                                .head(Head.builder()
                                        .title("Existing Recipe")
                                        .build())
                                .build())
                        .build())
                .build();
        List<RecipeComplete> mockRecipesList = new ArrayList<>();
        mockRecipesList.add(existingRecipe);

        RecipesDTO mockRecipesDTO = new RecipesDTO();
        mockRecipesDTO.setRecipesList(mockRecipesList);
        when(recipesRepository.getRecipesDTO()).thenReturn(mockRecipesDTO);

        RecipesException exception = assertThrows(RecipesException.class, () -> recipesOperation.addNewRecipe(newRecipe));
        assertEquals("The recipe: 'Existing Recipe' already exists", exception.getMessage());
        verify(recipesRepository, times(0)).setRecipesDTO(any(RecipesDTO.class));
    }
}