package com.library.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FineCalculator {

    public static final int FINE_PER_DAY = 10;
    public static final int FREE_TRAIL = 7;
    public static int calculateFine(LocalDate issueDate, LocalDate returnDate){
        long totalDays = ChronoUnit.DAYS.between(issueDate, returnDate);
        if (totalDays <= FREE_TRAIL) {
            return 0;
        }
        return (int) (totalDays - FREE_TRAIL) * FINE_PER_DAY;
    }
}
