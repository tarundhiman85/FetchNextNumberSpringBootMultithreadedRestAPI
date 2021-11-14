package com.example.yebelo.service;

import com.example.yebelo.model.Number;

import java.util.List;
import java.util.Optional;

public interface NumberService {
    void getNumber(Number num);
    List<Number> findALLNumbers();


    Optional<Number> findNById(int id);
}
