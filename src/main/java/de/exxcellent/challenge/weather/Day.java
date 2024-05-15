package de.exxcellent.challenge.weather;

public class Day {
    private int number;
    private int maximum;
    private int minimum;

    Day(int number, int maximum, int minimum) {
        this.number = number;
        this.maximum = maximum;
        this.minimum = minimum;
    }

    public int getTemperatureSpread() {
        return maximum - minimum;
    }

    public int getNumber() {
        return number;
    }
}
