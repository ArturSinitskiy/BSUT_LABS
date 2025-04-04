package org.example;

public class BasicCard implements ElectronicCard {
    private String name;

    public BasicCard(String name) {
        this.name = name;
    }

    @Override
    public void showInfo() {
        System.out.println("Владелец стандартной карты: " + name);
    }
}
