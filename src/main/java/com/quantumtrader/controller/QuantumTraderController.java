package com.quantumtrader.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.quantumtrader.entity.Stock;
import com.quantumtrader.service.QuantService;

@RestController
@RequestMapping("/api")
public class QuantumTraderController {

    private QuantService portfolioService;

    @Autowired
    public QuantumTraderController(QuantService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @PostMapping("/user/signup")
    public ResponseEntity<String> addUser(@RequestParam String id, @RequestParam int investment) {
        if (portfolioService.addUser(id, investment)) {
            return new ResponseEntity<>("Created account with username " + id, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Account with username " + id + " already exists.", HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(value = "/user/checkstock", produces = "application/json")
    public ResponseEntity<Stock> returnStock(@RequestParam String id, @RequestParam String stockname) {
        stockname = stockname.toLowerCase();
        Stock ret = this.portfolioService.returnStock(id, stockname);
        if (ret != null) {
            return new ResponseEntity<>(ret, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @ResponseBody
    @GetMapping("/user/logs")
    public ResponseEntity<String> returnLogs(@RequestParam String id) {
        String ret = this.portfolioService.getLogs(id);
        if (id != null) {
            return new ResponseEntity<>(ret, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/user/checkportfolio", produces = "application/json")
    public ResponseEntity<List<Stock>> getPortfolio(@RequestParam String id) {
        List<Stock> ret = this.portfolioService.getCurrentPortfolio(id);
        if (ret != null) {
            return new ResponseEntity<>(ret, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
}
