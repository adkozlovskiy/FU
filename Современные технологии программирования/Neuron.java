package com.financial;

import java.util.ArrayList;

public class Neuron {
    ArrayList<Double> xse, weights;
    int b;

    Neuron(ArrayList<Double> xse, ArrayList<Double> weights, int b){
        this.xse = xse;
        this.weights = weights;
        this.b = b;
    }

    double getResult(){
       return activation(xse.get(0) * weights.get(0) + xse.get(1) * weights.get(1) + b);
    }

    double activation(double x){
        return 1 / (1 + Math.exp(-1 * x));
    }
}
