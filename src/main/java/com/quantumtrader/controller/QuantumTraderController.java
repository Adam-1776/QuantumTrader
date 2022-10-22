package com.quantumtrader.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.quantumtrader.entity.Stock;

@RestController
@RequestMapping("/api")
public class QuantumTraderController {

    private Stock stock = new Stock("BBY", 25, 44);

    @GetMapping(value = "/user", produces = "application/json")
    public ResponseEntity<Stock> returnStock(@RequestParam int id, @RequestParam String stockname) {
        stockname = stockname.toLowerCase();
        if (id == 3 && stockname.equals("bby")) {
            System.out.println("Returning the following:" + stock.toString());
            return new ResponseEntity<>(stock, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @ResponseBody
    @GetMapping("/user/logs")
    public ResponseEntity<String> returnLogs(@PathVariable int id) {
        if (id == 3) {
            System.out.println("Returning the following:" + stock.toString());
            return new ResponseEntity<>(stock.toString(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
}
