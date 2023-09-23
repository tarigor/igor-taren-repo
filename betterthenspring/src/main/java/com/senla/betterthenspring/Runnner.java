package com.senla.betterthenspring;

import com.senla.container.CreateInstanceAndPutInContainer;

@CreateInstanceAndPutInContainer
public class Runnner {
    
    public static void main(String[] args) {
        Container.storeAllAnnotatedClassesToContainer();
        Container.injectAnnotatedFields();
        
    }
}
