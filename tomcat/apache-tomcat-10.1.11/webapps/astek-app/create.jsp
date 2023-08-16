<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <title>Business trip reimbursement</title>
    <link rel="stylesheet" href="css/style.css">
    <script src="js/script1.js" defer></script>
</head>

<body data-dailyAllowanceRate="${dailyAllowanceRate}" data-mileageRate="${mileageRate}">
    <main>

        <form novalidate action="create" method="post">
            <h2>Create a new reimbursement claim</h2>
            <div class="form-section">
                <label>Trip date:</label>
                <input name="tripDate" id="tripDate" type="date">
                <span class="error"> error message </span>
            </div>

            <div class="form-section">
                <label>Choose receipts:</label>
                <div class="dropdown">
                    <button type="button" class="button dropbtn">Receipts</button>
                    <ul name="options" class="options">
                        <c:forEach var="receipt" items="${receiptList}" >
                            <li data-value="${receipt.name} (${receipt.price}$)">${receipt.name} (${receipt.price}$)</li>
                        </c:forEach>
                    </ul>
                </div>
                <span class="error"> error message </span>

                <ul name="receiptList" id="receiptList"></ul>
            </div>

            <div class="form-section">
                <label>Daily allowance:</label>
                <div class="date-section">
                    <button type="button" class="button" onclick="addTimeframe()">Set timeframe</button>
                    <div class="flex-inline">
                        <p>Number of days:</p>
                        <input class="checkbox" type="checkbox" id="numOfDaysCB" />
                    </div>
                </div>

                <div class="flex-inline">
                    <input name="numOfDaysInput" id="numOfDaysInput" type="number" placeholder="number of days"
                        class="invisible">
                    <input name="startDate" id="startDate" type="date">
                    <input name="endDate" id="endDate" type="date">
                </div>

                <input readonly class="item" name="targetTimeframe" id="targetTimeframe" type=" text" value="0">

                <div class="flex-inline">
                    <button type="button" class="button" onclick="addExcludedDate()">Exclude</button>
                    <input name="excludedDate" id="excludedDate" type="date">
                </div>
                <span class="error"> error message </span>
                <ul name="dateList" id="dateList"></ul>
            </div>

            <div class="form-section">
                <label>Add personal car mileage (km):</label>
                <input name="mileage" type="number" placeholder="mileage" value="0">
                <span class="error"> error message </span>
            </div>

            <input id="btnSubmit" class="button" type="submit" value="create new reimbursement">
        </form>
    </main>
</body>

</html>