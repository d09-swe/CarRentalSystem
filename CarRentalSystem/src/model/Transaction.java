package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Transaction {

    private LocalDate dueDate;
    private LocalDate returnDate;
    private double fine;

    public Transaction(LocalDate dueDate) {
        this.dueDate = dueDate;
        this.returnDate = null;
        this.fine = 0.0;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public double calculateFine(double dailyFine) {
        LocalDate checkDate = (returnDate != null) ? returnDate : LocalDate.now();

        if (checkDate.isAfter(dueDate)) {
            long daysOverdue = ChronoUnit.DAYS.between(dueDate, checkDate);
            this.fine = daysOverdue * dailyFine;
        } else {
            this.fine = 0.0;
        }
        return this.fine;
    }
}
