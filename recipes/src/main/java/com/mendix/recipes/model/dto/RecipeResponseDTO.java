package com.mendix.recipes.model.dto;

import com.mendix.recipes.model.rest.Recipeml;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipeResponseDTO {

    private String message;
    private Recipeml recipeml;
}
