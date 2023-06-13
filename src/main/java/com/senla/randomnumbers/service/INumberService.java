package com.senla.randomnumbers.service;

public interface INumberService {
    Integer getRandomNaturalNumber(int countOfDigits);

    Integer getSumOfDigits(int number);
}
