package com.financial;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<Double> xse = new ArrayList<> ();
        ArrayList<Double> weights = new ArrayList<>();

        xse.add(2.0);
        xse.add(3.0);

        weights.add(0.0);
        weights.add(1.0);
        int b = 4;
        Neuron neuron = new Neuron(xse, weights, b);
        System.out.println(neuron.getResult());
    }
}
