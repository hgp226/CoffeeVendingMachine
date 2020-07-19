package com.dunzo.vendingmachine.exceptions;

public class UnknownIngredientException extends Exception {
    public UnknownIngredientException(String message) {
        super(message);
    }
}
