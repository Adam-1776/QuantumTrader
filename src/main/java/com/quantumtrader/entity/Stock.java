package com.quantumtrader.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import java.time.Instant;

@Entity(name = "Stock")
public class Stock {

    @Id //This column is the primary key for the table
    @Column(nullable = false, updatable = false) //The value in this column for a given row must not be false, it must not be updated once created.
    private String stockName;

    @Column
    private int numShares;

    @Column
    private int purchasePrice;

    @Column
    private int currentPrice;

    @Column
    private Instant timeLastUpdated;

    @Column
    private Instant purchaseTime;

    @Column
    private int currentValue;

    public Stock() {

    }

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

    public int getNumShares() {
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
