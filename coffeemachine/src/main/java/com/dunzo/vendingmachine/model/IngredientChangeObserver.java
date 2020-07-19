package com.dunzo.vendingmachine.model;

import com.dunzo.vendingmachine.IngredientStore;

public interface IngredientChangeObserver {
    void notifyChange(IngredientStore.StoreItem storeItem);
}
