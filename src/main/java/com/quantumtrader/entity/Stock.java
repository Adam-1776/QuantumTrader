package com.quantumtrader.entity;

import java.time.Instant;

public class Stock {
    private String stockName;
    private int numShares;
    private int purchasePrice;
    private int currentPrice;
    private Instant timeLastUpdated;
    private Instant purchaseTime;
    private int currentValue;

    public Stock(String stockName, int numShares, int purchasePrice) {
        this.stockName = stockName;
        this.numShares = numShares;
        this.purchasePrice = purchasePrice;
        this.currentPrice = purchasePrice;
        this.purchaseTime = Instant.now();
        this.timeLastUpdated = this.purchaseTime;
        this.currentValue = purchasePrice * numShares;
    }

    @Override
    public String toString() {
        String ret = "\nstockName = " + this.stockName + "\n" +
            "numShares = " + this.numShares + "\n" +
            "purchasePrice = " + this.purchasePrice + "\n" +
            "purchaseTime = " + this.purchaseTime.toString() + "\n" +
            "currentPrice = " + this.currentPrice + "\n" +
            "timeLastUpdated = " + this.timeLastUpdated + "\n" +
            "investmentCost = " + investmentCost() + "\n" +
            "currentValue = " + getCurrentValue() + "\n\n";
            return ret;
    }

    public String getStockName() {
        return this.stockName;
    }

    public int getNumshares() {
        return this.numShares;
    }

    public int getPurchasePrice() {
        return this.purchasePrice;
    }

    public int getCurrentPrice() {
        return this.currentPrice;
    }

    public Instant getTimeLastUpdated() {
        return this.timeLastUpdated;
    }

    public Instant getPurchaseTime() {
        return this.purchaseTime;
    }

    public int updateCurrentPrice() {
        //fetch latest price and update currentPrice
        this.timeLastUpdated = Instant.now();
        return this.currentPrice;
    }

    public int getCurrentValue() {
        this.currentValue = updateCurrentPrice() * numShares;
        return this.currentValue;
    }

    public int getValue() {
        return this.currentValue;
    }

    public int investmentCost() {
        return purchasePrice * numShares;
    }
    
}
