package com.mendix.recipes.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mendix.recipes.model.RecipeComplete;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class RecipesRepository {

    @Value("${recipes.chili.jsonFileName}")
    private String chiliJsonFile;

    @Getter
    @Setter
    private RecipeComplete recipeml;

    @PostConstruct
    public void loadChiliList(){

        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(chiliJsonFile);

        try {
            recipeml = objectMapper.readValue(file, RecipeComplete.class);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
