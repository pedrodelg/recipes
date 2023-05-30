package com.mendix.recipes.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mendix.recipes.model.rest.RecipeComplete;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RecipesDTO {

    @JsonProperty("recipes")
    @NotNull
    private List<RecipeComplete> recipesList;

}
