package util;

import model.Booking;

public class InvoiceGenerator {

    public static String generateInvoice(Booking booking) {
        return "Invoice total (including VAT): $" + booking.calculateTotal();
    }
}
