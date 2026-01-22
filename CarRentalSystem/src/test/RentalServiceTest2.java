package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Car;
import model.Customer;
import service.RentalService;

public class RentalServiceTest {

    private RentalService rentalService;

    @BeforeEach
    void setup() {
        rentalService = new RentalService();
    }

    @Test
    void rentCar_shouldMakeCarUnavailable() {
        Customer customer = new Customer("Djonis", "Alliu", "djonis@mail.com", "999");
        Car car = new Car("Mercedes", "C200", 70.0, true);

        // NDRYSHO metodën sipas projektit tënd (p.sh. rent(), createBooking(), rentCar() etj.)
        rentalService.rentCar(customer, car, 2);

        assertFalse(car.isAvailable(), "Car should become unavailable after renting");
    }

    @Test
    void rentCar_shouldThrow_whenCarNotAvailable() {
        Customer customer = new Customer("A", "B", "a@mail.com", "1");
        Car car = new Car("Ford", "Focus", 30.0, false);

        assertThrows(IllegalStateException.class, () -> {
            rentalService.rentCar(customer, car, 1);
        });
    }

    @Test
    void returnCar_shouldMakeCarAvailable() {
        Car car = new Car("Toyota", "Corolla", 35.0, false);

        // NDRYSHO sipas metodës tënde (returnCar(), giveBack(), etc.)
        rentalService.returnCar(car);

        assertTrue(car.isAvailable());
    }
}
