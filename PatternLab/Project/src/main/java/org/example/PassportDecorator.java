package org.example;

public class PassportDecorator extends CardDecorator {
    private String passportNumber;

    public PassportDecorator(ElectronicCard decoratedCard, String passportNumber) {
        super(decoratedCard);
        this.passportNumber = passportNumber;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Номер паспорта: " + passportNumber);
    }
}
