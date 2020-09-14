package com.company;

class Comparator {

    private int[] values;
    private int[] gravity;

    Comparator(int[] values, int[] gravity){
        this.values = values;
        this.gravity = gravity;
    }

    int getRandomNumber(){
        int total = 0;
        for (int o : gravity) total += o; // sum(gravity)
        int num = 1 + (int) (Math.random() * total);

        int n = 0;
        for (int i = 0; i < values.length; i++){
            n += gravity[i];

            if (n >= num){
                return values[i];
            }
        }

        return -1;
    }
}
