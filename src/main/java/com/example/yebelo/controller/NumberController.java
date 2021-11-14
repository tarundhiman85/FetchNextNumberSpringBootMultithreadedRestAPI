package com.example.yebelo.controller;

import com.example.yebelo.YebeloApplication;
import com.example.yebelo.model.Number;
import com.example.yebelo.service.NumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/Number")
public class NumberController {

    @Autowired
    private NumberService numberService;

    public NumberController(NumberService numberService) {
        this.numberService = numberService;
    }
    private void sleep(int args){
        try{
            TimeUnit.SECONDS.sleep(args);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @PostMapping()
    public Number getNumber(@RequestBody Number number){
        numberService.getNumber(number);
        if(numberService.findNById(number.getCategoryCode()).isPresent()){
            sleep(5);
            return numberService.findNById(number.getCategoryCode()).get();
        }
        else{
            sleep(5);
            return number;
        }
    }
    @GetMapping()
    public List<Number> allNumber(){
        return numberService.findALLNumbers();
    }
}
