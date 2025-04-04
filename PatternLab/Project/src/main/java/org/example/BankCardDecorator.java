package org.example;

public class BankCardDecorator extends CardDecorator {
    private String cardNumber;

    public BankCardDecorator(ElectronicCard decoratedCard, String cardNumber) {
        super(decoratedCard);
        this.cardNumber = cardNumber;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Номер банковской карты: " + cardNumber);
    }
}
