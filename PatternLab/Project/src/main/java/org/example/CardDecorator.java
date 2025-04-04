package org.example;

public abstract class CardDecorator implements ElectronicCard {
    protected ElectronicCard decoratedCard;

    public CardDecorator(ElectronicCard decoratedCard) {
        this.decoratedCard = decoratedCard;
    }

    @Override
    public void showInfo() {
        decoratedCard.showInfo();
    }
}

