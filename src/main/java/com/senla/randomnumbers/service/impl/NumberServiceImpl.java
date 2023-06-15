package com.senla.randomnumbers.service.impl;

import com.senla.randomnumbers.service.INumberService;

import java.util.*;

public class NumberServiceImpl implements INumberService {
    @Override
    public Integer getRandomNaturalNumber(int countOfDigits) {
        int count = 0;
        int randomNumber = 0;
        while (count != countOfDigits) {
            randomNumber = new java.util.Random().nextInt(Integer.parseInt(String.valueOf(getDigitsLimit(countOfDigits))));
            count = Integer.toString(randomNumber).split("").length;
        }
        return randomNumber;
    }

    @Override
    public Integer getSumOfDigits(int number) {
        return Arrays.stream(Integer.valueOf(number).toString().split("")).mapToInt(Integer::parseInt).sum();
    }

    @Override
    public Set<Integer> getRandomNumbers(int countOfNumbers, int countOfDigits) {
        Set<Integer> integers = new HashSet<>();
        for (int c = 0; c < countOfNumbers; c++) {
            integers.add(getRandomNaturalNumber(countOfDigits));
        }
        integers.forEach(System.out::println);
        return integers;
    }

    private int getDigitsLimit(int countOfDigits) {
        return Integer.parseInt("9" + "9".repeat(Math.max(0, countOfDigits - 1)));
    }

    @Override
    public Integer getSumOfCertainDigitsInNumbers(Set<Integer> integers, int digitPosition) {
        List<Integer> numbers = new ArrayList<>(integers);
        if(digitPosition>numbers.get(0).toString().split("").length){
            throw new IllegalArgumentException("the digital position is exceed of the numbers of digits in number");
        }
        List<Integer> digits = new ArrayList<>();
        for (Integer number: numbers){
            digits.add(Integer.parseInt(String.valueOf(number).split("")[digitPosition-1]));
        }
        digits.forEach(System.out::println);
        return digits.stream().mapToInt(Integer::intValue).sum();
    }
}
