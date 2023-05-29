package com.mendix.recipes.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mendix.recipes.model.rest.RecipeComplete;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RecipesDTO {

    @JsonProperty("recipes")
    private List<RecipeComplete> recipesList;
}
