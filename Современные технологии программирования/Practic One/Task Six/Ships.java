package com.company;

import java.util.ArrayList;
import java.util.List;

abstract class Ships {

    public int countOfDecker;
    public int countAliveDecker;
    Coordinates[] coordinates;
    List<Coordinates> aroundCoordinates;

    public void setShip(int number, int letter) {
        this.countAliveDecker = 1;
        this.countOfDecker = 1;
        this.coordinates = new Coordinates[countOfDecker];
        coordinates[0] = new Coordinates(number, letter);
        this.validation();
    }

    public void setShip(char direction, int countOfDecker, int number, int letter) {
        this.countAliveDecker = countOfDecker;
        this.countOfDecker = countOfDecker;
        this.coordinates = new Coordinates[countOfDecker];
        direction = Character.toLowerCase(direction);
        for (int i = 0; i < countOfDecker; i++) {
            switch (direction) {
                case 'n':
                    coordinates[i] = new Coordinates(number, letter - i);
                    break;
                case 'e':
                    coordinates[i] = new Coordinates(number + i, letter);
                    break;
                case 's':
                    coordinates[i] = new Coordinates(number, letter + i);
                    break;
                case 'w':
                    coordinates[i] = new Coordinates(number - i, letter);
                    break;
                default:
                    System.out.println("Направление неверно!");
            }
        }
        this.validation();
    }

    public abstract void addShipToTheField();

    void fillAroundAreaCoordinates(){
        aroundCoordinates = new ArrayList<>();
        for (Coordinates c : this.coordinates) {
            int number = c.number;
            int letter = c.letter;
            for (int j = number - 1; j < number + 2; j++) {
                for (int i = letter - 1; i < letter + 2; i++) {
                    if (j > -1 && j < Battle.getSizeOfField() && i > -1 && i < Battle.getSizeOfField()) {
                        int count = 0;
                        Coordinates newCoordinates = new Coordinates(j, i);
                        for (Coordinates coordinate : this.coordinates) {
                            if (coordinate.equals(newCoordinates)) {
                                count++;
                            }
                        }
                        if (count == 0) {
                            aroundCoordinates.add(newCoordinates);
                        }
                    }
                }
            }
        }
    }

    public abstract void areaAroundTheShip();

    protected abstract void validation();

    boolean validationOnField(){
        for (int i = 0; i < this.countOfDecker; i++) {
            if (this.coordinates[i].number < 0 || this.coordinates[i].number > Battle.getSizeOfField() - 1) {
                return false;
            }
            if (this.coordinates[i].letter < 0 || this.coordinates[i].letter > Battle.getSizeOfField() - 1) {
                return false;
            }
        }
        return true;
    }

    public abstract boolean validationOtherShip();
}
