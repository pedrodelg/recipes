package com.mendix.recipes.operation;

import com.mendix.recipes.model.dto.RecipesDTO;
import com.mendix.recipes.repository.RecipesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecipesOperationImpl implements RecipesOperation{

    private final RecipesRepository recipesRepository;

    @Override
    public RecipesDTO getAllRecipes() {

        return recipesRepository.getRecipesDTO();
    }
}
