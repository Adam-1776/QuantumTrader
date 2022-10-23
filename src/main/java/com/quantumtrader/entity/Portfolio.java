package com.quantumtrader.entity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class Portfolio {

    private Map<String,Stock> portfolio;
    private SortedSet<String> holdings;
    private String id;
    private Instant timeLastUpdated;
    private int moneyInvested;
    private int moneyNotInvested;
    private int portfolioValue;

    public Portfolio(String id, int initialInvestment) {
        this.id = id;
        this.portfolio = new HashMap<String,Stock>();
        this.holdings = new TreeSet<String>();
        this.timeLastUpdated = Instant.now();
        this.moneyInvested = 0;
        this.moneyNotInvested = initialInvestment;
        this.portfolioValue = initialInvestment;
    }

    public int addNewStock(String stockName, int purchasePrice, int numShares) {
        if (holdings.contains(stockName)) {
            return 0; //Already holding this stock
        }
        if ((purchasePrice * numShares) > this.moneyNotInvested) {
            return -1; //Not enough cash to make this purchase
        }
        Stock newStock = new Stock(stockName, numShares, purchasePrice);
        this.holdings.add(stockName);
        this.portfolio.put(newStock.getStockName(), newStock);
        this.timeLastUpdated = Instant.now();
        this.moneyNotInvested -= (purchasePrice * numShares);
        this.moneyInvested += (purchasePrice * numShares);
        return 1;
    }

    public Stock getStock(String stockName) {
        if (!holdings.contains(stockName)) {
            return null; //not holding this stock
        }
        return this.portfolio.get(stockName);
    }

    public String getId() {
        return this.id;
    }

    public Instant getTimeLastUpdated() {
        return this.timeLastUpdated;
    }

    public int getMoneyInvested() {
        return this.moneyInvested;
    }

    public int getMoneyNotInvested() {
        return this.moneyNotInvested;
    }

    public int getCurrentPortfolioValue() {
        int temp = 0;
        for(String holding: this.holdings) {
            temp += this.portfolio.get(holding).getCurrentValue();
        }
        this.portfolioValue = temp + this.moneyNotInvested;
        return this.portfolioValue;
    }

    public int getPortfolioValue() {
        return this.portfolioValue;
    }

    public List<Stock> getCurrentPortfolio() {
        getCurrentPortfolioValue();
        return new ArrayList<Stock>(this.portfolio.values());
    }

    public List<Stock> getPortfolio() {
        return new ArrayList<Stock>(this.portfolio.values());
    }

    public String getLogs() {
        String temp = "moneyInvested = " + this.moneyInvested +
            "\nmoneyNotInvested = " + this.moneyNotInvested +
            "\nportfolioValue = " + this.portfolioValue;
        for(String holding: this.holdings) {
            temp += this.portfolio.get(holding).toString();
        }
        return temp;
    }
    
}
