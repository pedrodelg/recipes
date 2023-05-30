package com.mendix.recipes.model.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Recipe {

    private Head head;
    private Ingredients ingredients;
    private Directions directions;
}
