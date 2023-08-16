package org.example.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.example.model.Reimbursement.calculateDailyAllowance;
import static org.example.model.Reimbursement.calculateMileage;


public class ReimbursementTest {
    @Test
    public void calculateDailyAllowance_endDateBeforeStartDate_throwsIllegalArgumentException() {
        String tripDateStr = "2023-08-15";
        String targetTimeframeStr = "From 2023-08-30 to 2023-08-20";
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> calculateDailyAllowance(tripDateStr, targetTimeframeStr, null));
    }

    @Test
    public void calculateMileage_correctMileage_doesNotAnyException() {
        Assertions.assertDoesNotThrow(() -> calculateMileage("20.0"));
    }
}
