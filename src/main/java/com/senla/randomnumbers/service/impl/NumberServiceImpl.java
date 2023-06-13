package com.senla.randomnumbers.service.impl;

import com.senla.randomnumbers.service.INumberService;

import java.util.Arrays;

public class NumberServiceImpl implements INumberService {
    @Override
    public Integer getRandomNaturalNumber(int maxNumber) {
        if (Integer.valueOf(maxNumber).toString().length() != 3) {
            throw new IllegalArgumentException("It should be 3 digital number");
        }
        return new java.util.Random().nextInt(maxNumber);
    }

    @Override
    public Integer getSumOfDigits(int number) {
        return Arrays.stream(Integer.valueOf(number).toString().split("")).mapToInt(Integer::parseInt).sum();
    }
}
