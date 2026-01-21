package model;

import java.io.Serializable;

public class Booking implements Serializable {

    private Customer customer;
    private Car car;
    private int days;

    public Booking(Customer customer, Car car, int days) {
        this.customer = customer;
        this.car = car;
        this.days = days;
    }

    public double calculateTotal() {
        double vat = 0.15;
        return car.getDailyRate() * days * (1 + vat);
    }
}
