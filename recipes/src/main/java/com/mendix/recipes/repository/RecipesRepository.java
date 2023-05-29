package com.mendix.recipes.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mendix.recipes.model.dto.RecipesDTO;
import com.mendix.recipes.model.rest.RecipeComplete;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class RecipesRepository {

    @Value("${recipes.chili.jsonFileName}")
    private String chiliJsonFile;

//    @Value("${recipes.amaretto.jsonFileName}")
//    private String amarettoJsonFile;

    @Value("${recipes.zucchini.jsonFileName}")
    private String zucchiniJsonFile;

    @Getter
    @Setter
    private RecipesDTO recipesDTO;

    @PostConstruct
    public void  fillRecipeList() throws IOException {

        List<RecipeComplete> recipeCompleteList = Arrays.asList(loadChiliList(chiliJsonFile)/*, loadChiliList(amarettoJsonFile)*/, loadChiliList(zucchiniJsonFile));
        RecipesDTO recipesDTOFill = new RecipesDTO();
        recipesDTOFill.setRecipesList(recipeCompleteList);

        recipesDTO = recipesDTOFill;
    }

    public RecipeComplete loadChiliList(String jsonFile) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(jsonFile);

        return objectMapper.readValue(file, RecipeComplete.class);

    }
}
