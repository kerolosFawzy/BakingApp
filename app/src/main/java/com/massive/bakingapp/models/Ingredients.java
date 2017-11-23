package com.massive.bakingapp.models;

import com.fasterxml.jackson.annotation.*;

public class Ingredients {
    private String ingredient;
    private String measure;
    private double quantity;

    @JsonProperty("ingredient")
    public String getIngredient() { return ingredient; }
    @JsonProperty("ingredient")
    public void setIngredient(String value) { this.ingredient = value; }

    @JsonProperty("measure")
    public String getMeasure() { return measure; }
    @JsonProperty("measure")
    public void setMeasure(String value) { this.measure = value; }

    @JsonProperty("quantity")
    public double getQuantity() { return quantity; }
    @JsonProperty("quantity")
    public void setQuantity(double value) { this.quantity = value; }
}

