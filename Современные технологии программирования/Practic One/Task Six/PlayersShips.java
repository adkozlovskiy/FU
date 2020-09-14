package com.company;

import java.util.ArrayList;
import java.util.List;

public class PlayersShips extends Ships {
    public static final List<PlayersShips> shipsList = new ArrayList<>();

    public static int countOfBattleShip;
    public static int countOfCruiser;
    public static int countOfDestroyer;
    public static int countOfSubmarine;
    public static int countAliveShip;

    public static void clearAll(){
        shipsList.clear();
        countOfBattleShip = 0;
        countOfCruiser = 0;
        countOfDestroyer = 0;
        countOfSubmarine = 0;
        countAliveShip = 0;
    }

    @Override
    public void addShipToTheField(){
        for (Coordinates c : this.coordinates) {
            Battle.getPlayerField()[c.letter][c.number] = Battle.getShip();
        }
    }
    @Override
    public void areaAroundTheShip() {
        for (Coordinates c : this.aroundCoordinates) {
            Battle.getPlayerField()[c.letter][c.number] = Battle.getFail();
        }
    }

    public void validation(){
        if(this.validationOnField()){
            if (this.validationOtherShip()){
                shipsList.add(this);
                switch (countOfDecker) {
                    case 4:
                        countOfBattleShip++;
                        break;
                    case 3:
                        countOfCruiser++;
                        break;
                    case 2:
                        countOfDestroyer++;
                        break;
                    case 1:
                        countOfSubmarine++;
                        break;
                    default:
                        System.out.println("Ошибка");
                }
                addShipToTheField();
                fillAroundAreaCoordinates();
                countAliveShip++;
            }
        }
    }

    @Override
    public boolean validationOtherShip(){
        for (int i = 0; i < this.countOfDecker; i++) {
            int thisNumber = this.coordinates[i].number;
            int thisLetter = this.coordinates[i].letter;
            for (PlayersShips sh : shipsList) {
                for (int j = 0; j < sh.coordinates.length; j++ ) {
                    int shipNumber = sh.coordinates[j].number;
                    int shipLetter = sh.coordinates[j].letter;
                    if ((thisNumber == shipNumber && thisLetter == shipLetter)
                            || (thisNumber == shipNumber && thisLetter == shipLetter - 1)
                            || (thisNumber == shipNumber && thisLetter == shipLetter + 1)) {
                        return false;
                    }
                    if ((thisNumber == shipNumber - 1 && thisLetter == shipLetter)
                            || (thisNumber == shipNumber - 1 && thisLetter == shipLetter - 1)
                            || (thisNumber == shipNumber - 1 && thisLetter == shipLetter + 1)) {
                        return false;
                    }
                    if ((thisNumber == shipNumber + 1 && thisLetter == shipLetter)
                            || (thisNumber == shipNumber + 1 && thisLetter == shipLetter - 1)
                            || (thisNumber == shipNumber + 1 && thisLetter == shipLetter + 1)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
