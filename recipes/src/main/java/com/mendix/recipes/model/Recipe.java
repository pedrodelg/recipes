package com.mendix.recipes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Recipe {

    private Head head;
    private Ingredients ingredients;
    private Directions directions;
}
