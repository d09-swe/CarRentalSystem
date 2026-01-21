package gui;

import model.Car;
import service.RentalService;

import javax.swing.*;

public class DashboardFrame extends JFrame {

    public DashboardFrame() {

        RentalService service = new RentalService();

        service.addCar(new Car("Economy", "Yaris", "Toyota", 50000, 40, 1.3));
        service.addCar(new Car("SUV", "RAV4", "Toyota", 30000, 70, 2.5));

        JList<Car> carList = new JList<>(
                service.getAvailableCars().toArray(new Car[0]));

        JButton bookButton = new JButton("Book Selected Car");

        bookButton.addActionListener(e -> {
            Car selectedCar = carList.getSelectedValue();
            if (selectedCar != null) {
                new BookingFrame(selectedCar);
            }
        });

        add(new JScrollPane(carList), "Center");
        add(bookButton, "South");

        setTitle("Car Rental Dashboard");
        setSize(400, 300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
