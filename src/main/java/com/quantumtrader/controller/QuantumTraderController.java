package com.quantumtrader.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quantumtrader.entity.Stock;

@RestController
@RequestMapping("/api")
public class QuantumTraderController {

    private Stock stock = new Stock("BBY", 25, 44);

    @GetMapping(value = "/test/{id}", produces = "application/json")
    public int returntest(@PathVariable int id) {
        return id*2;
    }

    @GetMapping(value = "/stock/{id}", produces = "application/json")
    public ResponseEntity<Stock> returnStock(@PathVariable int id) {
        if (id == 3) {
            System.out.println("Returning the following:" + stock.toString());
            return new ResponseEntity<>(stock, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
}
