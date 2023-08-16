package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Receipt;
import org.example.model.Reimbursement;

import java.io.IOException;

import static org.example.model.Receipt.createNewListOfReceipts;
import static org.example.model.Reimbursement.*;


public class AdminServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String dailyAllowanceRate = req.getParameter("dailyAllowanceRate");
        String mileageRate = req.getParameter("mileageRate");
        String[] receiptNames = req.getParameterValues("receiptName");
        String[] receiptPrices = req.getParameterValues("receiptPrice");
        String[] receiptTypeLimits = req.getParameterValues("receiptTypeLimit");
        String totalReimbursementLimit = req.getParameter("totalReimbursementLimit");
        String distanceLimit = req.getParameter("distanceLimit");

        String info = "";
        try {
            setDailyAllowanceRate(Double.parseDouble(dailyAllowanceRate));
            setMileageRate(Double.parseDouble(mileageRate));
            Receipt.receipts.clear();
            createNewListOfReceipts(receiptNames, receiptPrices, receiptTypeLimits);
            setTotalReimbursementLimit(Double.parseDouble(totalReimbursementLimit));
            setMileageDistanceLimit(Double.parseDouble(distanceLimit));

            info = "The changes have been applied";
        } catch (IllegalArgumentException e) {
            info = "ERROR: " + e.getMessage();
        } catch (Exception e) {
            info = "UNEXPECTED ERROR: " + e.getMessage();
        } finally {
            req.setAttribute("info", info);
            this.getServletContext().getRequestDispatcher("/result.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("dailyAllowanceRate", Reimbursement.getDailyAllowanceRate());
        req.setAttribute("mileageRate", Reimbursement.getMileageRate());
        req.setAttribute("receiptList", Receipt.receipts);
        req.setAttribute("totalReimbursementLimit", Reimbursement.getTotalReimbursementLimit());
        req.setAttribute("distanceLimit", Reimbursement.getMileageDistanceLimit());

        this.getServletContext().getRequestDispatcher("/admin.jsp").forward(req, resp);
    }
}
