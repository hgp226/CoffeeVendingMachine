package com.dunzo.vendingmachine;

import com.dunzo.vendingmachine.exceptions.UnknownIngredientException;
import com.dunzo.vendingmachine.model.Beverage;
import com.dunzo.vendingmachine.model.BeverageType;
import com.dunzo.vendingmachine.model.Ingredient;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        // Build the vending machine
        IngredientStore.Builder builder = new IngredientStore.Builder();
        builder.addIngredient(Ingredient.WATER, 200, 50);
        builder.addIngredient(Ingredient.MILK, 200, 50);
        builder.addIngredient(Ingredient.TEA_SYRUP, 100, 25);
        builder.addIngredient(Ingredient.ELAICHI_SYRUP, 100, 25);
        builder.addIngredient(Ingredient.GINGER_SYRUP, 100, 25);
        builder.addIngredient(Ingredient.COFFEE_SYRUP, 100, 25);
        builder.addIngredient(Ingredient.SUGAR_SYRUP, 100, 25);
        IngredientStore ingredientStore = builder.build();

        Outlet outlet = new Outlet(4);

        VendingMachine vendingMachine = new VendingMachine(outlet, ingredientStore);

        // Serve beverages
        Beverage gingerTea = new Beverage(BeverageType.GINGER_TEA);
        Beverage elaichiTea = new Beverage(BeverageType.ELAICHI_TEA);
        Beverage coffee = new Beverage(BeverageType.COFFEE);
        Beverage milk = new Beverage(BeverageType.HOT_MILK);
        Beverage water = new Beverage(BeverageType.HOT_WATER);

        vendingMachine.serveBeverage(gingerTea); sleep();
        vendingMachine.serveBeverage(elaichiTea); sleep();
        vendingMachine.serveBeverage(water); sleep();
        vendingMachine.serveBeverage(milk); sleep();
        vendingMachine.serveBeverage(coffee); sleep();

        Beverage beverage1 = new Beverage(BeverageType.HOT_MILK);
        try {
            beverage1.setConstituent(Ingredient.WATER, 0);
            beverage1.setConstituent(Ingredient.MILK, 100);
        } catch (UnknownIngredientException e) {
            System.out.println(e.getMessage());
        }
        vendingMachine.serveBeverage(beverage1); sleep();

        // Refill ingredients
        try {
            vendingMachine.refillIngredient(Ingredient.MILK, 200);
            vendingMachine.refillIngredient(Ingredient.WATER, 200);
        } catch (UnknownIngredientException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void sleep() {
        try {
            TimeUnit.SECONDS.sleep(0L);
        } catch (InterruptedException e) {

        }
    }
}
