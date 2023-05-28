package com.mendix.recipes.operation;

import com.mendix.recipes.model.RecipeComplete;
import com.mendix.recipes.repository.RecipesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecipesOperationImpl implements RecipesOperation{

    private final RecipesRepository chiliRepository;

    @Override
    public RecipeComplete getAllRecipes() {

        return chiliRepository.getRecipeml();
    }
}
