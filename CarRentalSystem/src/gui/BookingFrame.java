package gui;

import model.Booking;
import model.Car;
import model.Customer;
import util.InvoiceGenerator;

import javax.swing.*;

public class BookingFrame {

    public BookingFrame(Car car) {

        String daysInput = JOptionPane.showInputDialog("Enter number of rental days:");
        int days = Integer.parseInt(daysInput);

        Customer customer = new Customer(
                "John", "Doe", "Male",
                "john@email.com", "123456", "City Center"
        );

        Booking booking = new Booking(customer, car, days);
        car.setAvailable(false);

        JOptionPane.showMessageDialog(null,
                InvoiceGenerator.generateInvoice(booking));
    }
}

