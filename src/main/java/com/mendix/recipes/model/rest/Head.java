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
public class Head {

    @Valid
    @NotNull
    private String title;
    @Valid
    @NotNull
    private Categories categories;
    @Valid
    @NotNull
    private Integer yield;
}
