package com.mendix.recipes.controller;

import com.mendix.recipes.model.dto.CategoriesDTO;
import com.mendix.recipes.model.dto.RecipeResponseDTO;
import com.mendix.recipes.model.dto.RecipesDTO;
import com.mendix.recipes.model.rest.*;
import com.mendix.recipes.operation.RecipesOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RecipesControllerTest {

    @Mock
    private RecipesOperation recipesOperation;

    @InjectMocks
    private RecipesController recipesController;

    RecipesDTO expectedResponse = RecipesDTO.builder()
            .recipesList(Collections.singletonList(
                    RecipeComplete.builder()
                            .recipeml(Recipeml.builder()
                                    .recipe(Recipe.builder()
                                            .head(Head.builder()
                                                    .title("30 Minute Chili")
                                                    .categories(Categories.builder()
                                                            .cat(Arrays.asList("Main dish", "Chili"))
                                                            .build())
                                                    .yield(6)
                                                    .build())
                                            .ingredients(Ingredients.builder()
                                                    .ingList(Arrays.asList(
                                                            Ing.builder()
                                                                    .amt(Amt.builder()
                                                                            .qty(1)
                                                                            .unit("pound")
                                                                            .build())
                                                                    .item("Ground chuck or lean ground beef")
                                                                    .build()
                                                    ))
                                                    .build())
                                            .directions(Directions.builder()
                                                    .step("Brown the meat in a little butter and cook until the meat is brown")
                                                    .build())
                                            .build())
                                    .build())
                            .build()
            ))
            .build();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        recipesController = new RecipesController(recipesOperation);

    }

    @Test
    public void test_get_recipes_without_query_parameters(){

        when(recipesOperation.getRecipes(null,null)).thenReturn(expectedResponse);

        ResponseEntity<RecipesDTO> response = recipesController.getRecipes(null,null);

        verify(recipesOperation).getRecipes(null,null);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());

    }

    @Test
    void test_get_recipes_filtering_category() {

        String category = "Main dishs";

        when(recipesOperation.getRecipes(category, null)).thenReturn(expectedResponse);

        ResponseEntity<RecipesDTO> response = recipesController.getRecipes(category, null);

        verify(recipesOperation).getRecipes(category, null);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }

    @Test
    void test_add_new_recipe() {

        Recipeml newRecipe = new Recipeml();
        RecipeResponseDTO expectedResponse2 = RecipeResponseDTO.builder()
                .message("Recipe added successfully")
                .recipeml(Recipeml.builder()
                        .recipe(Recipe.builder()
                                .head(Head.builder()
                                        .title("30 Minute Chili")
                                        .categories(Categories.builder()
                                                .cat(Arrays.asList("Main dish", "Chili"))
                                                .build())
                                        .yield(6)
                                        .build())
                                .ingredients(Ingredients.builder()
                                        .ingList(Arrays.asList(
                                                Ing.builder()
                                                        .amt(Amt.builder()
                                                                .qty(1)
                                                                .unit("pound")
                                                                .build())
                                                        .item("Ground chuck or lean ground beef")
                                                        .build()
                                                // Add more ingredients as needed
                                        ))
                                        .build())
                                .directions(Directions.builder()
                                        .step("Brown the meat in a little butter...")
                                        .build())
                                .build())
                        .build())
                .build();

        when(recipesOperation.addNewRecipe(newRecipe)).thenReturn(expectedResponse2);
        ResponseEntity<RecipeResponseDTO> response = recipesController.addNewRecipe(newRecipe);

        verify(recipesOperation).addNewRecipe(newRecipe);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedResponse2, response.getBody());
    }

    @Test
    void test_get_categories() {

        CategoriesDTO expectedCategoriesDTO = new CategoriesDTO();

        List<String> categories = Arrays.asList("Test", "Vegetables");
        expectedCategoriesDTO.setCategories(categories);

        when(recipesOperation.getCategories()).thenReturn(expectedCategoriesDTO);

        RecipesController recipesController = new RecipesController(recipesOperation);

        ResponseEntity<CategoriesDTO> response = recipesController.getRecipes();

        assertEquals(HttpStatus.OK, response.getStatusCode());

        CategoriesDTO actualCategoriesDTO = response.getBody();
        assertEquals(expectedCategoriesDTO, actualCategoriesDTO);
    }

}
