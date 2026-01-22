package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import model.Transaction;

public class TransactionTest {

    // ---------- Boundary Value Tests ----------

    @Test
    void BVT_returnBeforeDueDate_noFine() {
        Transaction t = new Transaction(LocalDate.of(2024, 5, 10));
        t.setReturnDate(LocalDate.of(2024, 5, 9));
        assertEquals(0.0, t.calculateFine(5.0));
    }

    @Test
    void BVT_returnOnDueDate_noFine() {
        Transaction t = new Transaction(LocalDate.of(2024, 5, 10));
        t.setReturnDate(LocalDate.of(2024, 5, 10));
        assertEquals(0.0, t.calculateFine(5.0));
    }

    @Test
    void BVT_returnAfterDueDate_fineApplied() {
        Transaction t = new Transaction(LocalDate.of(2024, 5, 10));
        t.setReturnDate(LocalDate.of(2024, 5, 11));
        assertTrue(t.calculateFine(5.0) > 0);
    }

    // ---------- Equivalence Class Tests ----------

    @Test
    void EC1_returnBeforeDueDate_noFine() {
        Transaction t = new Transaction(LocalDate.of(2024, 5, 10));
        t.setReturnDate(LocalDate.of(2024, 5, 8));
        assertEquals(0.0, t.calculateFine(5.0));
    }

    @Test
    void EC3_returnAfterDueDate_fineCalculated() {
        Transaction t = new Transaction(LocalDate.of(2024, 5, 10));
        t.setReturnDate(LocalDate.of(2024, 5, 15));
        assertEquals(25.0, t.calculateFine(5.0));
    }

    @Test
    void EC4_returnDateNull_useCurrentDate() {
        Transaction t = new Transaction(LocalDate.now().minusDays(2));
        assertTrue(t.calculateFine(5.0) >= 0);
    }
}
