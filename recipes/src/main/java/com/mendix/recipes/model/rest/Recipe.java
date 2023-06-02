package com.mendix.recipes.model.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class Recipe {

    @Valid
    @NotNull
    private Head head;
    @Valid
    @NotNull
    private Ingredients ingredients;
    @Valid
    @NotNull
    private Directions directions;
}
