//package com.company;

abstract class SolidOfRevolution extends Shape {

    private double radius;

    // Constructor double r, w --> Shape.v
    SolidOfRevolution(double radius, double height) {
        super(Math.PI * Math.pow(radius, 2) * height);
        this.radius = radius;
    }

    // Constructor double r --> Shape.v
    SolidOfRevolution(double radius){
        super((4.0 / 3.0) * Math.PI * Math.pow(radius, 3));
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }
}
