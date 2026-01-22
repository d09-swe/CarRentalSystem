package gui;

import model.Car;
import service.RentalService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DashboardFrame extends JFrame {

    private final RentalService service;
    private final DefaultListModel<Car> listModel;
    private final JList<Car> carList;

    public DashboardFrame(RentalService service) {
        this.service = service;

        // ----- UI Components -----
        listModel = new DefaultListModel<>();
        carList = new JList<>(listModel);
        carList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JButton bookButton = new JButton("Book Selected Car");
        JButton addCarButton = new JButton("Add Car");

        // ----- Layout -----
        setLayout(new BorderLayout());
        add(new JScrollPane(carList), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(addCarButton);
        bottomPanel.add(bookButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // ----- Actions -----
        addCarButton.addActionListener(e -> addCarDialog());
        bookButton.addActionListener(e -> openBookingForSelectedCar());

        // ----- Window -----
        setTitle("Car Rental Dashboard");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Fill list at start
        refreshCarList();

        setVisible(true);
    }

    // Convenience constructor: creates service + seed data once
    public DashboardFrame() {
        this(new RentalService());

        // Seed initial cars only once
        service.addCar(new Car("Economy", "Yaris", "Toyota", 50000, 40, 1.3));
        service.addCar(new Car("SUV", "RAV4", "Toyota", 30000, 70, 2.5));
        refreshCarList();
    }

    private void refreshCarList() {
        listModel.clear();
        List<Car> availableCars = service.getAvailableCars();
        for (Car c : availableCars) {
            listModel.addElement(c);
        }
    }

    private void openBookingForSelectedCar() {
        Car selectedCar = carList.getSelectedValue();
        if (selectedCar == null) {
            JOptionPane.showMessageDialog(this,
                    "Please select a car first.",
                    "No Selection",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        new BookingFrame(selectedCar);
    }

    private void addCarDialog() {
        // Simple input dialog (good for System Test screenshot)
        JTextField typeField = new JTextField();
        JTextField modelField = new JTextField();
        JTextField brandField = new JTextField();
        JTextField mileageField = new JTextField();
        JTextField pricePerDayField = new JTextField();
        JTextField engineField = new JTextField();

        Object[] message = {
                "Type (e.g. Economy, SUV):", typeField,
                "Model (e.g. Yaris):", modelField,
                "Brand (e.g. Toyota):", brandField,
                "Mileage (number):", mileageField,
                "Price per day (number):", pricePerDayField,
                "Engine (number):", engineField
        };

        int option = JOptionPane.showConfirmDialog(this, message,
                "Add New Car", JOptionPane.OK_CANCEL_OPTION);

        if (option != JOptionPane.OK_OPTION) return;

        try {
            String type = typeField.getText().trim();
            String model = modelField.getText().trim();
            String brand = brandField.getText().trim();

            int mileage = Integer.parseInt(mileageField.getText().trim());
            double pricePerDay = Double.parseDouble(pricePerDayField.getText().trim());
            double engine = Double.parseDouble(engineField.getText().trim());

            if (type.isEmpty() || model.isEmpty() || brand.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Type, Model, and Brand cannot be empty.",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            service.addCar(new Car(type, model, brand, mileage, pricePerDay, engine));
            refreshCarList();

            JOptionPane.showMessageDialog(this,
                    "Car added successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Mileage, Price per day, and Engine must be valid numbers.",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}

