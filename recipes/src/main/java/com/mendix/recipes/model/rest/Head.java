package com.mendix.recipes.model.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Head {


    private String title;
    private Categories categories;
    private Integer yield;
}
