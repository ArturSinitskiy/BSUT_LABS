package org.example;

public class InsuranceDecorator extends CardDecorator {
    private String policyNumber;

    public InsuranceDecorator(ElectronicCard decoratedCard, String policyNumber) {
        super(decoratedCard);
        this.policyNumber = policyNumber;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Страховой полис: " + policyNumber);
    }
}
