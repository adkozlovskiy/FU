package com.company;

public class Main {

    public static void main(String[] args) {
        ObservableStringBuilder observableStringBuilder = new ObservableStringBuilder("Привет, ");
        observableStringBuilder.setOnChangeListener(builder ->
                System.out.println("Новое состояние: " + builder.toString()));

        observableStringBuilder.append("мир!");
    }
}
