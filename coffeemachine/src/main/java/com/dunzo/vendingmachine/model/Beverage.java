package com.dunzo.vendingmachine.model;

import com.dunzo.vendingmachine.exceptions.UnknownIngredientException;

import java.util.Map;

public class Beverage {
    protected Map<Ingredient, Integer> constituents;
    private BeverageType type;

    public Beverage(BeverageType type) {
        this.type = type;
        constituents = type.getConstituents();
    }

    public Map<Ingredient, Integer> getConstituents() {
        return constituents;
    }

    public void setConstituent(Ingredient ingredient, int quantity) throws UnknownIngredientException {
        if (constituents.containsKey(ingredient)) {
            constituents.put(ingredient, quantity);
        } else {
            throw new UnknownIngredientException(String.format("Ingredient [%s] not valid for beverage [%s]", ingredient, type));
        }
    }

    public BeverageType getType() {
        return type;
    }

    public void setType(BeverageType type) {
        this.type = type;
    }
}
