package model;

import java.io.Serializable;

public abstract class Vehicle implements Serializable {

    protected String category;
    protected String model;
    protected String manufacturer;
    protected double mileage;
    protected double dailyRate;
    protected boolean available;

    public Vehicle(String category, String model, String manufacturer,
                   double mileage, double dailyRate) {
        this.category = category;
        this.model = model;
        this.manufacturer = manufacturer;
        this.mileage = mileage;
        this.dailyRate = dailyRate;
        this.available = true;
    }

    public abstract double getDailyRate();

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getModel() {
        return model;
    }
}
