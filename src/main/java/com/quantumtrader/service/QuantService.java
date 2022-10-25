package com.quantumtrader.service;

import com.quantumtrader.entity.Portfolio;
import com.quantumtrader.entity.Stock;
import com.quantumtrader.repository.StockRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class QuantService {

    private StockRepository stockRepository;

    private Map<String,Portfolio> portfolios = new HashMap<>();

    public QuantService() {
        
    }

    public boolean addUser(String id, int initialInvestment) {
        if (portfolios.containsKey(id)) {
            return false;
        }
        this.portfolios.put(id, new Portfolio(id, initialInvestment));
        return true;
    }

    public int addNewStock(String id, String stockName, int numShares, int purchasePrice) {
        if (!portfolios.containsKey(id)) {
            return -2; //No portfolio with this id exists
        }
        Stock newStock = new Stock(stockName, numShares, purchasePrice);
        int ret = this.portfolios.get(id).addNewStock(newStock);
        if (ret == 1) {
            stockRepository.save(newStock);
        }
        return ret;
    }

    public Stock returnStock(String id, String stockName) {
        if (!portfolios.containsKey(id)) {
            return null; //No portfolio with this id exists
        }
        return this.portfolios.get(id).getStock(stockName);
    }

    public List<Stock> getCurrentPortfolio(String id) {
        if (!portfolios.containsKey(id)) {
            return null; //No portfolio with this id exists
        }
        return this.portfolios.get(id).getCurrentPortfolio();
    }

    public String getLogs(String id) {
        if (!portfolios.containsKey(id)) {
            return null; //No portfolio with this id exists
        }
        return this.portfolios.get(id).getLogs();
    }
    
}
