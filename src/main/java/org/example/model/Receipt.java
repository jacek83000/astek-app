package org.example.model;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Receipt {
    public static ArrayList<Receipt> receipts = new ArrayList<>();
    private String name;
    //e
    private double price;
    private int typeLimit;

    public Receipt(String name, double price, int typeLimit) {
        setName(name);
        setPrice(price);
        setTypeLimit(typeLimit);
        receipts.add(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(Objects.equals(name, "")) {
            throw new IllegalArgumentException("Name can't be empty.");
        } else {
            this.name = name;
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if(0.0 >= price) {
            throw new IllegalArgumentException("Price can't be smaller or equal 0.");
        } else {
            this.price = price;
        }
    }

    public int getTypeLimit() {
        return typeLimit;
    }

    public void setTypeLimit(int typeLimit) {
            this.typeLimit = typeLimit;
    }

    public boolean tooManyForThisType(int amountOfReceipts) {
        return amountOfReceipts > this.typeLimit;
    }


    public static void createNewListOfReceipts(
            String[] receiptNames,
            String[] receiptPrices,
            String[] receiptTypeLimits) {
        if(receiptNames != null) {
            for (int i = 0; i < receiptNames.length; i++) {
                isStringEmpty(receiptPrices[i], "Receipt price can't be empty.");
                isStringEmpty(receiptTypeLimits[i], "Receipt type limit can't be empty.");

                new Receipt(
                        receiptNames[i],
                        Double.parseDouble(receiptPrices[i]),
                        Integer.parseInt(receiptTypeLimits[i]));
            }
        } else {
            throw new IllegalArgumentException("Receipts needs to have at least one receipt.");
        }
    }

    public static ArrayList<Receipt> receiptStringsToReceiptList(String[] receipts) {
        ArrayList<Receipt> receiptsList = new ArrayList<>();

        if(receipts != null) {
            for (String receiptString : receipts) {
                for (Receipt receipt : Receipt.receipts) {
                    if (receiptString.contains(receipt.getName())) {
                        receiptsList.add(receipt);
                    }
                }
            }
        }

        return receiptsList;
    }

    public static Map<Receipt, Long> countReceiptTypes(ArrayList<Receipt> receipts) {
        return receipts.stream()
                .collect(Collectors.groupingBy(Function.identity(),
                        Collectors.counting())
                );
    }



    public static void isStringEmpty(String s, String errMessage) {
        if(Objects.equals(s, "")) {
            throw new IllegalArgumentException(errMessage);
        }
    }
}
