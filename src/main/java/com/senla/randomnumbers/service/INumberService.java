package com.senla.randomnumbers.service;

import java.util.Set;

public interface INumberService {
    Integer getRandomNaturalNumber(int countOfDigits);

    Integer getSumOfDigits(int number);

    Set<Integer> getRandomNumbers(int countOfNumbers, int countOfDigits);

    Integer getSumOfCertainDigitsInNumbers(Set<Integer> integers, int digitPosition);
}
