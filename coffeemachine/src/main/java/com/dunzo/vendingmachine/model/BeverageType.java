package com.dunzo.vendingmachine.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public enum BeverageType {
    HOT_WATER((constituents) -> {
        constituents.put(Ingredient.WATER, 100);
    }),
    HOT_MILK((constituents) -> {
        constituents.put(Ingredient.WATER, 40);
        constituents.put(Ingredient.MILK, 60);
    }),
    COFFEE((constituents) -> {
        constituents.put(Ingredient.WATER, 40);
        constituents.put(Ingredient.MILK, 50);
        constituents.put(Ingredient.COFFEE_SYRUP, 5);
        constituents.put(Ingredient.SUGAR_SYRUP, 5);
    }),
    GINGER_TEA((constituents) -> {
        constituents.put(Ingredient.WATER, 40);
        constituents.put(Ingredient.MILK, 50);
        constituents.put(Ingredient.TEA_SYRUP, 5);
        constituents.put(Ingredient.GINGER_SYRUP, 5);
        constituents.put(Ingredient.SUGAR_SYRUP, 5);
    }),
    ELAICHI_TEA((constituents) -> {
        constituents.put(Ingredient.WATER, 40);
        constituents.put(Ingredient.MILK, 50);
        constituents.put(Ingredient.TEA_SYRUP, 5);
        constituents.put(Ingredient.ELAICHI_SYRUP, 5);
        constituents.put(Ingredient.SUGAR_SYRUP, 5);
    });

    private List<Ingredient> defaultIngredients;
    private Consumer<Map<Ingredient, Integer>> defaultConstituentsConsumer;

    BeverageType(Consumer<Map<Ingredient, Integer>> defaultConstituentsConsumer) {
        this.defaultConstituentsConsumer = defaultConstituentsConsumer;
    }

    public Map<Ingredient, Integer> getConstituents() {
        Map<Ingredient, Integer> defaultConstituents = new HashMap<>();
        defaultConstituentsConsumer.accept(defaultConstituents);
        return defaultConstituents;
    }
}
