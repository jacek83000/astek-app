package org.example.model;

import org.example.operation.CalendarOperations;

import java.text.ParseException;
import java.util.*;

import static org.example.model.Receipt.countReceiptTypes;
import static org.example.model.Receipt.receiptStringsToReceiptList;
import static org.example.operation.CalendarOperations.createSetWithFilledDaysInBetween;
import static org.example.operation.CalendarOperations.strDatesToCalendarSet;

public class Reimbursement {
    public static ArrayList<Reimbursement> reimbursements = new ArrayList<>();
    private static double dailyAllowanceRate = 15.00;
    private static double mileageRate = 0.3;

    private static double totalReimbursementLimit = 300.0;
    private static double mileageDistanceLimit = 30.0;

    private double result;

    public Reimbursement(double result) {
        this.result = result;
        reimbursements.add(this);
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public static double calculateReimbursement(
            String tripDateStr, String[] receiptsStr, String targetTimeframeStr,
            String[] excludedDates, String mileageStr) throws ParseException {

        double totalReceipts = calculateReceipts(receiptsStr);
        double totalDailyAllowance = calculateDailyAllowance(tripDateStr, targetTimeframeStr, excludedDates);
        double totalMileage = calculateMileage(mileageStr);
        double totalReimbursement = totalReceipts + totalDailyAllowance + totalMileage;

        if (totalReimbursement > totalReimbursementLimit) {
            throw new IllegalArgumentException("Total reimbursement can't be higher than " + totalReimbursementLimit + "$");
        }

        return totalReimbursement;
    }

    public static double calculateReceipts(String[] receiptsStr) {
        ArrayList<Receipt> receiptsList = receiptStringsToReceiptList(receiptsStr);
        Map<Receipt, Long> frequencyReceiptMap = countReceiptTypes(receiptsList);

        double sumReceipts = 0;
        for (Map.Entry<Receipt, Long> pair : frequencyReceiptMap.entrySet()) {
            Receipt receipt = pair.getKey();
            int amountOfReceipt = Math.toIntExact(pair.getValue());
            if (receipt.tooManyForThisType(amountOfReceipt)) {
                throw new IllegalArgumentException(
                        "Can't have more than " + receipt.getTypeLimit()
                                + " receipt(s) of type '" + receipt.getName()
                                + "'.");
            }
            sumReceipts += amountOfReceipt * receipt.getPrice();
        }

        return sumReceipts;
    }

    public static double calculateDailyAllowance(String tripDateStr, String targetTimeframeStr,
                                                 String[] excludedDatesStr) throws ParseException {
        //"yyyy-mm-dd" -> tripDateStr
        //"x days(s)" / "From yyyy-mm-dd to yyyy-mm-dd" -> targetTimeframeStr
        //["Excluded: yyyy-mm-dd", "Excluded: yyyy-mm-dd", ...] -> excludedDatesStr

        double sumDailyAllowance = 0.0;
        Calendar[] calendars = CalendarOperations.getTargetTimeframeCalendars(targetTimeframeStr, tripDateStr);

        if (calendars != null) {
            Calendar startDate = calendars[0];
            Calendar endDate = calendars[1];

            if(endDate.before(startDate)) {
                throw new IllegalArgumentException("Start date must be later than end date");
            }

            Set<Calendar> timeframe = createSetWithFilledDaysInBetween(startDate, endDate);
            Set<Calendar> excludedDates = strDatesToCalendarSet(excludedDatesStr);
            timeframe.removeAll(excludedDates);

            sumDailyAllowance += timeframe.size() * dailyAllowanceRate;
        }

        return sumDailyAllowance;
    }

    public static double calculateMileage(String mileageStr) {
        double mileage = Double.parseDouble(mileageStr);
        if (mileage < 0) {
            throw new IllegalArgumentException("Mileage can't be lower than 0 km");
        } else if (mileage > mileageDistanceLimit) {
            throw new IllegalArgumentException("Mileage can't be higher than " + mileageDistanceLimit + "km");
        }

        return mileage * mileageRate;
    }


    public static double getDailyAllowanceRate() {
        return dailyAllowanceRate;
    }

    public static void setDailyAllowanceRate(double dailyAllowanceRate) {
        if (0.0 >= dailyAllowanceRate) {
            throw new IllegalArgumentException("Daily allowance can't be smaller or equal 0.");
        } else {
            Reimbursement.dailyAllowanceRate = dailyAllowanceRate;
        }
    }

    public static double getMileageRate() {
        return mileageRate;
    }

    public static void setMileageRate(double mileageRate) {
        if (0.0 >= mileageRate) {
            throw new IllegalArgumentException("Mileage rate can't be smaller or equal 0.");
        } else {
            Reimbursement.mileageRate = mileageRate;
        }
    }

    public static double getTotalReimbursementLimit() {
        return totalReimbursementLimit;
    }

    public static void setTotalReimbursementLimit(double totalReimbursementLimit) {
        Reimbursement.totalReimbursementLimit = totalReimbursementLimit;
    }

    public static double getMileageDistanceLimit() {
        return mileageDistanceLimit;
    }

    public static void setMileageDistanceLimit(double mileageDistanceLimit) {
        Reimbursement.mileageDistanceLimit = mileageDistanceLimit;
    }
}
