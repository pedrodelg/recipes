package com.mendix.recipes.error;

public class RecipesException extends RuntimeException{

    public RecipesException(String message) {
        super(message);
    }

    public RecipesException(String message, Throwable cause) {
        super(message, cause);
    }

    public RecipesException(Throwable cause) {
        super(cause);
    }
}
