package com.senla.randomnumbers;

import com.senla.randomnumbers.service.INumberService;
import com.senla.randomnumbers.service.impl.NumberServiceImpl;

import java.util.Set;

public class Runner {
    public static void main(String[] args) {

        INumberService numberService = new NumberServiceImpl();

//Displays a randomly generated three-digit natural number and the sum of its digits.
        int randomNumber = numberService.getRandomNaturalNumber(3);
        System.out.println("The random number is -> " + randomNumber);
        System.out.println("sum -> " + numberService.getSumOfDigits(randomNumber));

//Display 3 randomly generated three-digit numbers and the sum of their first digits;
        Set<Integer> integerSet = numberService.getRandomNumbers(3, 3);
        System.out.println("sum is-> " + numberService.getSumOfCertainDigitsInNumbers(integerSet, 1));
    }
}
