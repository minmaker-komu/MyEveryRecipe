package com.example.myeveryrecipe;

public class MyRecipeData {
    private int recipe_image;
    private String recipe_name;
    private String recipe_food;

    public MyRecipeData(int recipe_image, String recipe_name, String recipe_food) {

        this.recipe_image = recipe_image;
        this.recipe_name = recipe_name;
        this.recipe_food = recipe_food;
    }

    public int getRecipe_image() {
        return recipe_image;
    }

    public void setRecipe_image(int recipe_image) {
        this.recipe_image = recipe_image;
    }

    public String getRecipe_name() {
        return recipe_name;
    }

    public void setRecipe_name(String recipe_name) {
        this.recipe_name = recipe_name;
    }

    public String getRecipe_food() {
        return recipe_food;
    }

    public void setRecipe_food(String recipe_food) {
        this.recipe_food = recipe_food;
    }
}
