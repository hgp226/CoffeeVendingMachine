package com.dunzo.vendingmachine;

import com.dunzo.vendingmachine.model.Beverage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Outlet {
    private ExecutorService executorService;
    private int numOutlets;

    public Outlet(int numOutlets) {
        this.numOutlets = numOutlets;
    }

    public void initialize() {
        executorService = Executors.newFixedThreadPool(numOutlets);
    }

    public void serveBeverage(Beverage beverage) {
        executorService.submit(() -> {
            System.out.println(String.format("%s is served", beverage.getType()));
        });
    }
}
