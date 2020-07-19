package com.dunzo.vendingmachine;

import com.dunzo.vendingmachine.model.Ingredient;
import com.dunzo.vendingmachine.model.IngredientChangeObserver;

import java.util.HashMap;
import java.util.Map;

public class AlertIndicator implements IngredientChangeObserver {
    private Map<Ingredient, Boolean> ingredientAlertMap;
    public AlertIndicator() {
        ingredientAlertMap = new HashMap<>();
    }

    public void notifyChange(IngredientStore.StoreItem storeItem) {
        ingredientAlertMap.putIfAbsent(storeItem.getIngredient(), false);
        if (storeItem.getQuantity() < storeItem.getAlertThreshold()) {
            lightOn(storeItem.getIngredient());
        } else {
            lightOff(storeItem.getIngredient());
        }
    }

    private void lightOn(Ingredient ingredient) {
        boolean currState = ingredientAlertMap.get(ingredient);
        if (!currState) {
            System.out.println(String.format("Indicator ON for ingredient %s", ingredient.name()));
            ingredientAlertMap.put(ingredient, true);
        }
    }

    private void lightOff(Ingredient ingredient) {
        boolean currState = ingredientAlertMap.get(ingredient);
        if (currState) {
            System.out.println(String.format("Indicator OFF for ingredient %s", ingredient.name()));
            ingredientAlertMap.put(ingredient, false);
        }
    }
}
