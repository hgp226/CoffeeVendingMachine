package com.dunzo.vendingmachine.exceptions;

public class InsufficientQuantityException extends Throwable {
    public InsufficientQuantityException(String message) {
        super(message);
    }
}
