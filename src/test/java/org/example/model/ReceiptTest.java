package org.example.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReceiptTest {
    @Test
    public void setName_nameEmpty_throwsIllegalArgumentException() {
        String name = "";
        double price = 30.00;
        int typeLimit = 1;
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Receipt(name, price, typeLimit));
    }

    @Test
    public void setPrice_notPositivePrice_throwsIllegalArgumentException() {
        String name = "taxi";
        double price  = -30.00;
        int typeLimit = 3;
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Receipt(name, price, typeLimit));
    }
}
