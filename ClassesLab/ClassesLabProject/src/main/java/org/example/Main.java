package org.example;

import java.util.ArrayList;
import java.util.List;

// Абстрактный класс года
abstract class Year {
    protected List<Month> months;
    protected int yearNumber;

    public Year(int yearNumber) {
        this.yearNumber = yearNumber;
        this.months = createMonths();
    }

    public abstract boolean isLeap();

    private List<Month> createMonths() {
        List<Month> months = new ArrayList<>();
        months.add(new Month("Январь", 31));
        months.add(new Month("Февраль", isLeap() ? 29 : 28));
        months.add(new Month("Март", 31));
        months.add(new Month("Апрель", 30));
        months.add(new Month("Май", 31));
        months.add(new Month("Июнь", 30));
        months.add(new Month("Июль", 31));
        months.add(new Month("Август", 31));
        months.add(new Month("Сентябрь", 30));
        months.add(new Month("Октябль", 31));
        months.add(new Month("Ноябрь", 30));
        months.add(new Month("Декабрь", 31));
        return months;
    }

    public void printYearInfo() {
        System.out.println("Year: " + yearNumber + " (" + (isLeap() ? "Високосный" : "Невисокосный") + ")");
        for (Month month : months) {
            System.out.println(month);
        }
    }
}

// Високосный год
class LeapYear extends Year {
    public LeapYear(int yearNumber) {
        super(yearNumber);
    }

    @Override
    public boolean isLeap() {
        return true;
    }
}

// Невисокосный год
class NonLeapYear extends Year {
    public NonLeapYear(int yearNumber) {
        super(yearNumber);
    }

    @Override
    public boolean isLeap() {
        return false;
    }
}

// Класс месяца
class Month {
    private String name;
    private int days;

    public Month(String name, int days) {
        this.name = name;
        this.days = days;
    }

    public String getName() {
        return name;
    }

    public int getDays() {
        return days;
    }

    @Override
    public String toString() {
        return name + ": " + days + " days";
    }
}

// Пример использования
public class Main {
    public static void main(String[] args) {
        Year leap2020 = new LeapYear(2020);
        leap2020.printYearInfo();

        Year nonLeap2021 = new NonLeapYear(2021);
        nonLeap2021.printYearInfo();
    }
}