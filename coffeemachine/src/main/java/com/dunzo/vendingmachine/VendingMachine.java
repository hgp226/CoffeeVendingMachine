package com.dunzo.vendingmachine;

import com.dunzo.vendingmachine.exceptions.InsufficientQuantityException;
import com.dunzo.vendingmachine.exceptions.UnknownIngredientException;
import com.dunzo.vendingmachine.model.Beverage;
import com.dunzo.vendingmachine.model.Ingredient;

import java.util.Map;

public class VendingMachine {
    private Outlet outlet;
    private IngredientStore ingredientStore;
    private AlertIndicator alertIndicator;

    public VendingMachine(Outlet outlet, IngredientStore ingredientStore) {
        this.outlet = outlet;
        this.ingredientStore = ingredientStore;
        this.alertIndicator = new AlertIndicator();
        outlet.initialize();
        ingredientStore.registerObserver(alertIndicator);
    }

    public void serveBeverage(Beverage beverage) {
        Map<Ingredient, Integer> constituents = beverage.getConstituents();
        for (Ingredient ingredient : constituents.keySet()) {
            int quantity = constituents.get(ingredient);
            int storeQuantity = 0;
            try {
                storeQuantity = ingredientStore.getQuantityForIngredient(ingredient);
            } catch (UnknownIngredientException e) {
                System.out.println(e.getMessage());
                return;
            }
            if (storeQuantity < quantity) {
                System.out.println(String.format("%s beverage cannot be prepared because %s item is not available", beverage.getType().name(), ingredient));
                return;
            }
        }
        for (Ingredient ingredient : constituents.keySet()) {
            int quantity = constituents.get(ingredient);
            try {
                ingredientStore.decreaseIngredientQuantity(ingredient, quantity);
            } catch (InsufficientQuantityException e) {
                // Cannot happen
            }
        }
        outlet.serveBeverage(beverage);
    }

    public void refillIngredient(Ingredient ingredient, int quantity) throws UnknownIngredientException {
        ingredientStore.increaseIngredientQuantity(ingredient, quantity);
    }
}
