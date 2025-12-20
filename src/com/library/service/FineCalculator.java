package com.library.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FineCalculator {

    public static int calculateFine(LocalDate issueDate, LocalDate returnDate){
        long totalDays = ChronoUnit.DAYS.between(issueDate, returnDate);
        int FREE_TRAIL = 7;
        if (totalDays <= FREE_TRAIL) {
            return 0;
        }
        int FINE_PER_DAY = 10;
        return (int) (totalDays - FREE_TRAIL) * FINE_PER_DAY;
    }
}
