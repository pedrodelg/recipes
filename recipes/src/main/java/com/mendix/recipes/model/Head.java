package com.mendix.recipes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Head {

    private String title;
    private Categories categories;
    private Integer yield;
}
