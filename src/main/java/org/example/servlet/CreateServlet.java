package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.Receipt;
import org.example.model.Reimbursement;

import java.io.IOException;
import java.text.ParseException;

import static org.example.model.Reimbursement.calculateReimbursement;

public class CreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String tripDate = req.getParameter("tripDate");
        String[] receipts = req.getParameterValues("receipt");
        String targetTimeframe = req.getParameter("targetTimeframe");
        String[] excludedDates = req.getParameterValues("excludedDates");
        String mileage = req.getParameter("mileage");

        String info = "";
        try {
            double totalReimbursement = calculateReimbursement(tripDate, receipts, targetTimeframe, excludedDates, mileage);
            new Reimbursement(totalReimbursement);

            info = "Total reimbursement: " + totalReimbursement + "$";
        } catch (IllegalArgumentException | ParseException e) {
            info = "ERROR: " + e.getMessage();
        } catch (Exception e) {
            info = "UNEXPECTED ERROR: " + e.getMessage();
        } finally {
            req.setAttribute("info", info);
            this.getServletContext().getRequestDispatcher("/result.jsp").forward(req, resp);
        }
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        req.setAttribute("dailyAllowanceRate", Reimbursement.getDailyAllowanceRate());
        req.setAttribute("mileageRate", Reimbursement.getMileageRate());
        req.setAttribute("receiptList", Receipt.receipts);

        this.getServletContext().getRequestDispatcher("/create.jsp").forward(req, res);
    }
}