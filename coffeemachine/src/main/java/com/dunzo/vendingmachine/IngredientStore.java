package com.dunzo.vendingmachine;

import com.dunzo.vendingmachine.exceptions.InsufficientQuantityException;
import com.dunzo.vendingmachine.exceptions.UnknownIngredientException;
import com.dunzo.vendingmachine.model.Ingredient;
import com.dunzo.vendingmachine.model.IngredientChangeObserver;

import java.util.*;

public class IngredientStore {
    private Map<Ingredient, StoreItem> ingredientStoreMap;
    private List<IngredientChangeObserver> ingredientChangeObservers;

    private IngredientStore() {
        ingredientStoreMap = new HashMap<>();
        ingredientChangeObservers = new LinkedList<>();
    }

    public void registerObserver(IngredientChangeObserver ingredientChangeObserver) {
        ingredientChangeObservers.add(ingredientChangeObserver);
    }

    public void increaseIngredientQuantity(Ingredient ingredient, int quantity) throws UnknownIngredientException {
        if (!ingredientStoreMap.containsKey(ingredient)) {
            throw new UnknownIngredientException(String.format("[%s] ingredient not found in store", ingredient));
        }
        StoreItem storeItem = ingredientStoreMap.get(ingredient);
        storeItem.setQuantity(storeItem.getQuantity() + quantity);
        notifyObservers(storeItem);
    }

    public void decreaseIngredientQuantity(Ingredient ingredient, int quantity) throws InsufficientQuantityException {
        StoreItem storeItem = ingredientStoreMap.get(ingredient);
        int currQuantity = storeItem.getQuantity();
        if (quantity > currQuantity) {
            throw new InsufficientQuantityException(String.format("Insufficient ingredient [%s] found", ingredient.name()));
        }
        storeItem.setQuantity(currQuantity - quantity);
        notifyObservers(storeItem);
    }

    public int getQuantityForIngredient(Ingredient ingredient) throws UnknownIngredientException {
        if (!ingredientStoreMap.containsKey(ingredient)) {
            throw new UnknownIngredientException(String.format("Ingredient [%s] not found in ingredient store"));
        }
        return ingredientStoreMap.get(ingredient).getQuantity();
    }

    private void addIngredient(Ingredient ingredient, int quantity, int alertThreshold) {
        ingredientStoreMap.putIfAbsent(ingredient, new StoreItem(ingredient, quantity, alertThreshold));
    }

    private void notifyObservers(StoreItem storeItem) {
        for (IngredientChangeObserver ingredientChangeObserver : ingredientChangeObservers) {
            ingredientChangeObserver.notifyChange(storeItem);
        }
    }

    public static class StoreItem {
        private Ingredient ingredient;
        private int quantity;
        private int alertThreshold;

        public StoreItem(Ingredient ingredient, int quantity, int alertThreshold) {
            this.ingredient = ingredient;
            this.quantity = quantity;
            this.alertThreshold = alertThreshold;
        }

        public Ingredient getIngredient() {
            return ingredient;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getAlertThreshold() {
            return alertThreshold;
        }

        public void setAlertThreshold(int alertThreshold) {
            this.alertThreshold = alertThreshold;
        }
    }

    public static class Builder {
        private IngredientStore ingredientStore;

        public Builder() {
            ingredientStore = new IngredientStore();
        }

        public Builder addIngredient(Ingredient ingredient, int quantity, int alertThreshold) {
            ingredientStore.addIngredient(ingredient, quantity, alertThreshold);
            return this;
        }

        public IngredientStore build() {
            return ingredientStore;
        }
    }

}
