package com.senla.randomnumbers.service.impl;

import com.senla.randomnumbers.service.INumberService;
import junit.framework.TestCase;

public class NumberServiceImplTest extends TestCase {
    private final INumberService numberService = new NumberServiceImpl();

    public void testGetSumOfDigits() {
        int randomNumber = numberService.getRandomNaturalNumber(988);
        System.out.println("The random number is -> " + randomNumber);
        System.out.println("sum -> " + numberService.getSumOfDigits(randomNumber));
    }
}