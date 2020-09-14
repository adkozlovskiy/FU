package com.company;

import java.io.*;
import java.util.Properties;

public class Battle {
    private static final String NEWLINE = System.lineSeparator();
    private static char space = ' ';
    private static char ship = '#';
    private static char fail = '•';
    private static char hit = 'X';
    private static int sizeOfField = 10;
    private static int maxCountOfBattleShip = 1;
    private static int maxCountOfCruiser = 2;
    private static int maxCountOfDestroyer = 3;
    private static int maxCountOfSubmarine = 4;

    private static final char[][] computerField = new char[sizeOfField][sizeOfField];
    private static final char[][] playerField = new char[sizeOfField][sizeOfField];

    static char getShip() {
        return ship;
    }

    static char getFail() {
        return fail;
    }

    static int getSizeOfField() {
        return sizeOfField;
    }

    static char[][] getComputerField() {
        return computerField;
    }

    static char[][] getPlayerField() {
        return playerField;
    }

    public static void main(String[] args){

        loadProperties();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String answer;
            do {
                // clear all data in ship class (actually for for repeated plays)
                ComputersShips.clearAll();
                PlayersShips.clearAll();
                System.out.print("Хотите разместить корабли вручную? [Y/n] ");
                answer = reader.readLine();
                if ("y".equals(answer.toLowerCase()) || "".equals(answer)) {
                    //manually placing players ships on the field
                    manualPlacingPlayersShips(reader);
                } else
                    //generate players ships on the field
                    generatePlayersShips();
                //generate computers ships on the field
                generateComputersField();

                // Turn-Based game
                while (PlayersShips.countAliveShip != 0 && ComputersShips.countAliveShip != 0) {
                    playerFireAndCheck(reader);
                    if (ComputersShips.countAliveShip != 0) {
                        computerFireAndCheck();
                    }
                }
                // If game is over
                System.out.print("Сыграем еще раз? [Y/n]: ");
                answer = reader.readLine();
            } while ("y".equals(answer.toLowerCase()) || "".equals(answer));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void manualPlacingPlayersShips(BufferedReader reader) throws IOException {
        //clear the field, fill with space character
        for (int i = 0; i < sizeOfField; i++) {
            for (int j = 0; j < sizeOfField; j++) {
                playerField[i][j] = 32;
            }
        }
        printPlayersField();
        manuallyCreatePlayersShip(reader, 4, maxCountOfBattleShip);
        manuallyCreatePlayersShip(reader, 3, maxCountOfCruiser);
        manuallyCreatePlayersShip(reader, 2, maxCountOfDestroyer);
        manuallyCreatePlayersShip(reader, 1, maxCountOfSubmarine);
    }

    private static void generateComputersField() {
        //clear the field, fill with space character
        for (int i = 0; i < sizeOfField; i++) {
            for (int j = 0; j < sizeOfField; j++) {
                computerField[i][j] = 32;
            }
        }
        createComputersShip(4, maxCountOfBattleShip);
        createComputersShip(3, maxCountOfCruiser);
        createComputersShip(2, maxCountOfDestroyer);
        createComputersShip(1, maxCountOfSubmarine);
    }

    private static void generatePlayersShips() {
        //clear the field, fill with space character
        for (int i = 0; i < sizeOfField; i++) {
            for (int j = 0; j < sizeOfField; j++) {
                playerField[i][j] = 32;
            }
        }
        createPlayersShip(4, maxCountOfBattleShip);
        createPlayersShip(3, maxCountOfCruiser);
        createPlayersShip(2, maxCountOfDestroyer);
        createPlayersShip(1, maxCountOfSubmarine);
    }

    private static void manuallyCreatePlayersShip(BufferedReader reader, int countOfDecker, int maxCountOfShip) throws IOException {
        //count of created ships
        int countOfShip = countOfDecker == 1 ? PlayersShips.countOfSubmarine : countOfDecker == 2 ?
                PlayersShips.countOfDestroyer : countOfDecker == 3 ? PlayersShips.countOfCruiser : PlayersShips.countOfBattleShip;

        while (countOfShip < maxCountOfShip) {
            System.out.println(String.format("Ставьте ваш %s!", countOfDecker == 1 ? "лодку" : countOfDecker == 2 ?
                    "катер" : countOfDecker == 3 ? "уничтожитель" : "крейсер"));

            if (countOfDecker != 1) {
                System.out.println("Запишите координаты (n - север, e - восток, w - запад, s - юг) " + NEWLINE + "К примеру: d2 e");
            } else {
                System.out.println("Пиши: координаты" + NEWLINE + "Например: a2");
            }

            String parameters = reader.readLine();
            // exit for exit from game.
            if ("exit".equals(parameters.toLowerCase())) {
                System.exit(0);
            }

            String[] param =  parameters.split("[ ]+");
            String coordinates = param[0];
            int number;
            int letter;
            try {
                letter = Character.toLowerCase(coordinates.charAt(0)) - 97;
                number = Integer.parseInt(coordinates.substring(1, coordinates.length())) - 1;
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод.");
                manuallyCreatePlayersShip(reader, countOfDecker, maxCountOfShip);
                break;
            }
            // Check coordinates, that they valid
            if ((number < 0 || number > sizeOfField - 1 ) || (letter < 0 || letter > sizeOfField - 1)){
                System.out.println("Ошибка в вводе.");
                manuallyCreatePlayersShip(reader, countOfDecker, maxCountOfShip);
                break;
            }
            //If creating ship is battleship, or Cruise, or Destroyer
            if (countOfDecker > 1) {
                //If we have directions parameter
                if (param.length > 1) {
                    char direction = param[1].charAt(0);
                    // Check the direction
                    if (direction != 'n' && direction != 'e' && direction != 's' && direction != 'w'){
                        System.out.println("Неверное направление.");
                        manuallyCreatePlayersShip(reader, countOfDecker, maxCountOfShip);
                        break;
                    }
                    // create ship and set parameters
                    PlayersShips playersShip = new PlayersShips();
                    playersShip.setShip(direction, countOfDecker, number, letter);
                    printPlayersField();
                    countOfShip = countOfDecker == 2 ? PlayersShips.countOfDestroyer : countOfDecker == 3 ? PlayersShips.countOfCruiser : PlayersShips.countOfBattleShip;
                } else {
                    System.out.println("Ты забыл указать направление.");
                    manuallyCreatePlayersShip(reader, countOfDecker, maxCountOfShip);
                    break;
                }
                // If creating ship is Submarine
            } else {
                // create submarine and set parameters
                PlayersShips playersShip = new PlayersShips();
                playersShip.setShip(number, letter);
                printPlayersField();
                countOfShip = PlayersShips.countOfSubmarine;
            }
        }
    }

    private static void createPlayersShip(int countOfDecker, int maxCountOfShip) {
        //generate players ships on the field
        int countOfShip = countOfDecker == 1 ? PlayersShips.countOfSubmarine : countOfDecker == 2 ?
                PlayersShips.countOfDestroyer : countOfDecker == 3 ? PlayersShips.countOfCruiser : PlayersShips.countOfBattleShip;

        while (countOfShip < maxCountOfShip) {
            // generate coordinates
            int randomNumber = (int) (Math.random() * sizeOfField);
            int randomLetter = (int) (Math.random() * sizeOfField);
            // generate coordinates
            if (countOfDecker > 1) {
                int randomDirection = (int) (Math.random()*4);
                char direction = randomDirection == 0 ? 'n': randomDirection == 1 ? 'e': randomDirection == 2 ? 's': 'w';
                // create ship and set parameters
                PlayersShips ship = new PlayersShips();
                ship.setShip(direction, countOfDecker, randomNumber, randomLetter);
                countOfShip = countOfDecker == 2 ? PlayersShips.countOfDestroyer : countOfDecker == 3 ?
                        PlayersShips.countOfCruiser : PlayersShips.countOfBattleShip;

            } else {
                // create submarine and set parameters
                PlayersShips ship = new PlayersShips();
                ship.setShip(randomNumber, randomLetter);
                countOfShip = PlayersShips.countOfSubmarine;
            }
        }
    }

    private static void createComputersShip(int countOfDecker, int maxCountOfShip){
        //generate computers ships on the field
        int countOfShip = countOfDecker == 1 ? ComputersShips.countOfSubmarine : countOfDecker == 2 ?
                ComputersShips.countOfDestroyer : countOfDecker == 3 ? ComputersShips.countOfCruiser : ComputersShips.countOfBattleShip;

        while (countOfShip < maxCountOfShip) {
            // generate coordinates
            int randomNumber = (int) (Math.random() * sizeOfField);
            int randomLetter = (int) (Math.random() * sizeOfField);
            // generate coordinates
            if (countOfDecker > 1) {
                int randomDirection = (int) (Math.random()*4);
                char direction = randomDirection == 0 ? 'n': randomDirection == 1 ? 'e': randomDirection == 2 ? 's': 'w';
                // create ship and set parameters
                ComputersShips ship = new ComputersShips();
                ship.setShip(direction, countOfDecker, randomNumber, randomLetter);
                countOfShip = countOfDecker == 2 ? ComputersShips.countOfDestroyer : countOfDecker == 3 ?
                        ComputersShips.countOfCruiser : ComputersShips.countOfBattleShip;

            } else {
                // create submarine and set parameters
                ComputersShips ship = new ComputersShips();
                ship.setShip(randomNumber, randomLetter);
                countOfShip = ComputersShips.countOfSubmarine;
            }
        }
    }

    private static void computerFireAndCheck() throws InterruptedException {

        int fireNumber = (int) (Math.random() * sizeOfField);
        int fireLetter= (int) (Math.random() * sizeOfField);

        if (playerField[fireLetter][fireNumber] == space) {
            playerField[fireLetter][fireNumber] = fail;
            printTheField();
            System.out.println("Компьютер не попал");
        } else if (playerField[fireLetter][fireNumber] == ship) {
            playerField[fireLetter][fireNumber] = hit;
            printTheField();
            System.out.println("Компьютер попал");
/***/            Thread.sleep(2000);
            for(PlayersShips playersShip: PlayersShips.shipsList) {
                for (int j = 0; j < playersShip.coordinates.length; j++ ) {
                    int shipNumber = playersShip.coordinates[j].number;
                    int shipLetter = playersShip.coordinates[j].letter;
                    if (fireNumber == shipNumber && fireLetter == shipLetter) {
                        playersShip.countAliveDecker--;
                        if (playersShip.countAliveDecker == 0){
                            int ignore = playersShip.countOfDecker==1? PlayersShips.countOfSubmarine-- : playersShip.countOfDecker==2?
                                    PlayersShips.countOfDestroyer-- : playersShip.countOfDecker==3?
                                    PlayersShips.countOfCruiser-- : PlayersShips.countOfBattleShip--;

                            playersShip.areaAroundTheShip();
                            printTheField();
                            System.out.println("Компьютер потопил твой корабль!");
                            PlayersShips.countAliveShip--;

                            if(PlayersShips.countAliveShip == 0) {
                                System.out.println("Компьютер выиграл!");
                                return;
                            }
                        }
                    }
                }
            }
            computerFireAndCheck();

        } else if (playerField[fireLetter][fireNumber] == hit
                || playerField[fireLetter][fireNumber] == fail) {
            computerFireAndCheck();
        }
    }


    private static void playerFireAndCheck(BufferedReader reader) throws IOException
    {
        printTheField();
        int fireNumber;
        int fireLetter;
        while (true) {
            System.out.print("Сделай ход: ");
            String fire = reader.readLine();

            // exit for exit from game.
            if ("exit".equals(fire.toLowerCase())) {
                System.exit(0);
            }
            if (fire.length()>1) {
                fireLetter = Character.toLowerCase(fire.charAt(0)) - 97;
                fireNumber = Integer.parseInt(fire.substring(1, fire.length())) - 1;
                if ((fireNumber > -1 && fireNumber < sizeOfField )
                        && (fireLetter > -1 && fireLetter < sizeOfField)) {
                    break;
                } else System.out.println("Неверные координаты");
            } else System.out.println("Неверные координаты");
        }

        if (computerField[fireLetter][fireNumber] == space) {
            computerField[fireLetter][fireNumber] = fail;
            printTheField();

        } else if (computerField[fireLetter][fireNumber] == ship) {
            computerField[fireLetter][fireNumber] = hit;
            System.out.println("Отлично! Ты попал!");
            for(ComputersShips computer: ComputersShips.shipsList) {
                for (int j = 0; j < computer.coordinates.length; j++ ) {
                    int shipNumber = computer.coordinates[j].number;
                    int shipLetter = computer.coordinates[j].letter;
                    if (fireNumber == shipNumber && fireLetter == shipLetter) {
                        computer.countAliveDecker--;
                        if (computer.countAliveDecker == 0){
                            int ignore = computer.countOfDecker==1? ComputersShips.countOfSubmarine-- : computer.countOfDecker==2?
                                    ComputersShips.countOfDestroyer-- : computer.countOfDecker==3?
                                    ComputersShips.countOfCruiser-- : ComputersShips.countOfBattleShip--;

                            computer.areaAroundTheShip();
                            System.out.println("Ты потопил корабль компьютера!");
                            ComputersShips.countAliveShip--;

                            if(ComputersShips.countAliveShip == 0) {
                                System.out.println("Поздравляю! Ты выиграл!");
                                printTheField();
                                return;
                            }
                        }
                    }
                }
            }
            System.out.println("Стреляй еще раз");
            playerFireAndCheck(reader);

        } else if (computerField[fireLetter][fireNumber] == hit
                || computerField[fireLetter][fireNumber] == fail) {
            System.out.println("Ты уже стрелял сюда!");
            playerFireAndCheck(reader);
        }
    }

    private static void loadProperties(){
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream("config.properties")) {
            properties.load(input);
            space = properties.getProperty("space").charAt(0);
            ship = properties.getProperty("ship").charAt(0);
            fail = properties.getProperty("fail").charAt(0);
            hit = properties.getProperty("hit").charAt(0);
            sizeOfField = Integer.parseInt(properties.getProperty("sizeOfField"));
            maxCountOfBattleShip = Integer.parseInt(properties.getProperty("maxCountOfBattleShip"));
            maxCountOfCruiser = Integer.parseInt(properties.getProperty("maxCountOfCruiser"));
            maxCountOfDestroyer = Integer.parseInt(properties.getProperty("maxCountOfDestroyer"));
            maxCountOfSubmarine = Integer.parseInt(properties.getProperty("maxCountOfSubmarine"));
        } catch (IOException e) {
            System.out.println("Поле загружено.");
        }
    }

    private static void printPlayersField()
    {
        System.out.println("   1  2  3  4  5  6  7  8  9  10");
        for (int i = 0; i < sizeOfField; i++)
        {
            System.out.print(String.format("%2.2s", (char)(i + 65)));
            for (int j = 0; j < sizeOfField; j++)
            {
                System.out.print("[" + playerField[i][j] + "]");
            }
            System.out.println();
        }
    }

    private static void printTheField()
    {
        System.out.println("              Игрок                                                Компьютер");
        System.out.println("    1  2  3  4  5  6  7  8  9  10                      1  2  3  4  5  6  7  8  9  10");
        for (int i = 0; i < sizeOfField; i++)
        {
            System.out.print(String.format("%2.2s ", (char)(i + 65)));
            for (int j = 0; j < sizeOfField; j++)
            {
                char cross = playerField[i][j];
                if (cross == Battle.hit) {
                    System.out.print('[');
                    System.out.print(cross);
                    System.out.print(']');
                } else if (cross == Battle.ship) {
                    System.out.print('[');
                    System.out.print(cross);
                    System.out.print(']');
                } else {
                    System.out.print('[');
                    System.out.print(cross);
                    System.out.print(']');
                }

            }

            String ship = i==0? "Лодки: " : i==1? "Катеры: " : i==2? "Уничтожит.: " : i==3? "Крейсеры: " : " ";
            String countOfPlayersShips = String.valueOf(i==0? PlayersShips.countOfBattleShip : i==1? PlayersShips.countOfCruiser : i==2?
                    PlayersShips.countOfDestroyer : i==3? PlayersShips.countOfSubmarine : "");
            String playersShips = String.format("  %-12s%1s   ", ship, countOfPlayersShips);
            System.out.print(playersShips);
            System.out.print(String.format("%2.2s ", (char)(i + 65)));
            for (int j = 0; j < sizeOfField; j++)
            {
                char cross = computerField[i][j] == Battle.ship ? ' ': computerField[i][j];
                if (cross == Battle.hit) {
                    System.out.print('[');
                    System.out.print(cross);
                    System.out.print(']');
                } else {
                    System.out.print('[');
                    System.out.print(cross);
                    System.out.print(']');
                }
            }
            String countOfComputersShips = String.valueOf(i==0? ComputersShips.countOfBattleShip : i==1? ComputersShips.countOfCruiser : i==2?
                    ComputersShips.countOfDestroyer : i==3? ComputersShips.countOfSubmarine : "");
            String computersShips = String.format("  %-12s%1s", ship, countOfComputersShips);
            System.out.println(computersShips);
        }
    }
}