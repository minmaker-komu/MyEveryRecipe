package com.example.myeveryrecipe;

public class MaterialData {

    private int material_image;
    private String material_name;
    private String material_date;
    private String material_due;
    private String material_num;

    public MaterialData(int material_image, String material_name, String material_date, String material_due, String material_num){
        this.material_image = material_image;
        this.material_name = material_name;
        this.material_date = material_date;
        this.material_due = material_due;
        this.material_num = material_num;
    }

    public int getMaterial_image() {
        return material_image;
    }

    public void setMaterial_image(int material_image) {
        this.material_image = material_image;
    }

    public String getMaterial_name() {
        return material_name;
    }

    public void setMaterial_name(String material_name) {
        this.material_name = material_name;
    }

    public String getMaterial_date() {
        return material_date;
    }

    public void setMaterial_date(String material_date) {
        this.material_date = material_date;
    }

    public String getMaterial_due() {
        return material_due;
    }

    public void setMaterial_due(String material_due) {
        this.material_due = material_due;
    }

    public String getMaterial_num() {
        return material_num;
    }

    public void setMaterial_num(String material_num) {
        this.material_num = material_num;
    }
}
