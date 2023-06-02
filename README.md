# REST API Recipes

REST API to get recipes and add a new one.

## 🚀 Getting started
1. Before build and run this API you should have installed Gradle on your system.
2. To compile the project execute below command on the root folder "recipes":
```cmd
$ gradle build
```
2. To run unit test execute below command on the root folder "recipe"
```cmd
$ gradle clean test --info
```
2. To run the project execute below command on the root folder "recipes":
```cmd
$ gradle bootRun
```
3. Now you project is running in port: 8081

## 🛠 Rest API methods

```text
POST /api/add-recipe
```
* Description: Used to add new recipes. Request body example:
```json
{
  "recipe": {
    "head": {
      "title": "Another Zucchini Dish",
      "categories": {
        "cat": [
          "test",
          "Vegetables"
        ]
      },
      "yield": 6
    },
    "ingredients": {
      "ing": [
        {
          "amt": {
            "qty": 1,
            "unit": "pound"
          },
          "item": "Zucchini; cubed 1/2 \""
        },
        {
          "amt": {
            "qty": 3,
            "unit": "tablespoons"
          },
          "item": "Butter or margarine"
        },
        {
          "amt": {
            "qty": 3,
            "unit": ""
          },
          "item": "Eggs; beaten"
        },
        {
          "amt": {
            "qty": 1,
            "unit": ""
          },
          "item": "Jar pimentos; 2 1/2 oz, diced"
        },
        {
          "amt": {
            "qty": 1,
            "unit": "cup"
          },
          "item": "Cheddar cheese; shredded"
        },
        {
          "amt": {
            "qty": 1,
            "unit": "can"
          },
          "item": "French fried onion rings 3 oz."
        }
      ]
    },
    "directions": {
      "step": "test"
    }
  }
}
```

```text
GET /api/get-recipes
```
* Description: Used to get all the recipes. It has 2 query parameters:
  1. category (String): value for filtering the recipes by category, you can set the complete 
     name of the category or and small piece, it works as search string as well
  2. nme (String): value for filtering the recipes by name, you can set the complete
     name of the recipe or and small piece, it works as search string as well

```text
GET /api/get-categories
```
* Description: Used to get all the categories of the recipes stored in memory.

#### You can find the postman collection inside the project in the following path: 

```text
/recipes/src/main/resources/Recipes.postman_collection.json
```

## 💻 Technologies:
* Java 17
* Spring Boot 3
* Gradle
* Lombok

Developed by [Pedro Delgadillo](https://github.com/pedrodelg)💻
