package com.company;

abstract class Shape {
    private double volume;

    Shape(double volume){
        this.volume = volume;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

}
