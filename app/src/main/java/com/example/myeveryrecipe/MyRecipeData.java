package com.example.myeveryrecipe;

public class MyRecipeData {


    // 레시피 이미지, 이름, 종류, 재료, 내용
    private int recipe_image;
    private String recipe_name;
    private String recipe_food;
    private String recipe_context;
    private String recipe_need;

    public MyRecipeData(int recipe_image, String recipe_name, String recipe_food, String recipe_context, String recipe_need) {

        this.recipe_image = recipe_image;
        this.recipe_name = recipe_name;
        this.recipe_food = recipe_food;
        this.recipe_context = recipe_context;
        this.recipe_need = recipe_need;
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

    public String getRecipe_context() {
        return recipe_context;
    }

    public void setRecipe_context(String recipe_context) {
        this.recipe_context = recipe_context;
    }

    public String getRecipe_need() {
        return recipe_need;
    }

    public void setRecipe_need(String recipe_need) {
        this.recipe_need = recipe_need;
    }
}
