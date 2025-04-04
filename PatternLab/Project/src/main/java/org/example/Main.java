package org.example;

public class Main {
    public static void main(String[] args) {
        ElectronicCard card = new BasicCard("Artur Sinitskiy");

        card = new PassportDecorator(card, "HB7584803");
        card = new InsuranceDecorator(card, "IN734045789");
        card = new BankCardDecorator(card, "4656 7476 4859 7395");

        card.showInfo();
    }
}
