package com.senla.init;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Test {

    public static void main(String[] args) throws Exception {
        Test test = new Test();
        test.findPackageNamesStartingWith().forEach(System.out::println);
    }

    public List<String> findPackageNamesStartingWith() {
        return Arrays.stream(Package.getPackages())
                .map(Package::getName)
                .collect(toList());
    }
}
