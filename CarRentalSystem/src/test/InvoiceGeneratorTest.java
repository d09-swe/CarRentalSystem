package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Booking;
import model.Car;
import model.Customer;
import util.InvoiceGenerator;

public class InvoiceGeneratorTest {

    @Test
    void generateInvoice_shouldContainTotalTextAndAmount() {
        Customer customer = new Customer("Elio", "Test", "elio@mail.com", "222");
        Car car = new Car("VW", "Golf", 40.0, true);

        Booking booking = new Booking(customer, car, 1);

        String invoice = InvoiceGenerator.generateInvoice(booking);

        assertNotNull(invoice);
        assertTrue(invoice.contains("Invoice total"));
        // kontroll minimal që ka një numër
        assertTrue(invoice.matches(".*\\d+.*"));
    }

    @Test
    void generateInvoice_shouldThrow_whenBookingNull() {
        assertThrows(NullPointerException.class, () -> InvoiceGenerator.generateInvoice(null));
    }
}
