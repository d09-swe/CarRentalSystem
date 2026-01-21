package model;

public class Car extends Vehicle {

    private double engineSize;

    public Car(String category, String model, String manufacturer,
               double mileage, double dailyRate, double engineSize) {
        super(category, model, manufacturer, mileage, dailyRate);
        this.engineSize = engineSize;
    }

    @Override
    public double getDailyRate() {
        return dailyRate;
    }

    @Override
    public String toString() {
        return model + " (" + category + ") - $" + dailyRate + " per day";
    }
}
