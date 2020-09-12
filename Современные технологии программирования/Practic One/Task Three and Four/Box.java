package com.company;

class Box extends Shape{

    // Constructor double v --> Shape.v
    Box (double volume) {
        super(volume);
    }

    boolean addShape(Shape shape){
        if (shape.getVolume() >= getVolume()){
            System.out.println("Не хватает места в контейнере.");
           return false;
        }

        System.out.println("Фигура добавлена в контейнер.");
        setVolume(getVolume() - shape.getVolume());
        return true;
    }

    //getter getVolume()
    @Override
    public double getVolume() {
        return super.getVolume();
    }

    //setter getVolume()
    @Override
    public void setVolume(double volume) {
        super.setVolume(volume);
    }
}
