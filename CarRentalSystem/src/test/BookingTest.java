package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Booking;
import model.Car;
import model.Customer;

public class BookingTest {

    @Test
    void calculateTotal_shouldIncludeVat() {
        Customer customer = new Customer("Ana", "Kola", "ana@mail.com", "111");
        Car car = new Car("Audi", "A4", 100.0, true); // 100/day

        Booking booking = new Booking(customer, car, 2); // 2 ditë

        double total = booking.calculateTotal();

        // Këtu po supozojmë: total = days * pricePerDay * (1 + VAT)
        // Nëse VAT është 20% => 100*2*1.2 = 240
        // NDRYSHO vatExpected sipas VAT real në projektin tënd
        double vatExpected = 0.20;
        double expected = 100.0 * 2 * (1 + vatExpected);

        assertEquals(expected, total, 0.0001);
    }

    @Test
    void calculateTotal_shouldThrow_whenDaysInvalid() {
        Customer customer = new Customer("Ana", "Kola", "ana@mail.com", "111");
        Car car = new Car("Audi", "A4", 100.0, true);

        assertThrows(IllegalArgumentException.class, () -> {
            new Booking(customer, car, 0); // 0 ditë s’duhet
        });
    }
}
