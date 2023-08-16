<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <title>Business trip reimbursement</title>
    <link rel="stylesheet" href="css/style.css">
    <script src="js/admin.js" defer></script>
</head>

<body data-dailyAllowanceRate="${dailyAllowanceRate}" data-carMileageRate="${carMileageRate}">
<main>

    <form novalidate action="admin" method="post">
        <h2>Admin</h2>

        <div class="form-section">
            <label>Daily allowance rate (per day):</label>
            <input name="dailyAllowanceRate" type="number" value="${dailyAllowanceRate}" placeholder="must be postive value">
        </div>

        <div class="form-section">
            <label>Mileage rate (per km):</label>
            <input name="mileageRate" type="number" value="${mileageRate}" placeholder="must be postive value">
        </div>

        <div class="form-section">
            <label>Limit for one receipt:</label>
            <button type="button" class="button delete-button" onclick="addReceipt()">Add receipt</button>
            <ul name="receiptList" id="receiptList">
                <c:forEach var="receipt" items="${receiptList}" >
                    <li>
                        <input class="item" name="receiptName" type="text" value="${receipt.name}" placeholder="name">
                        <input class="item" name="receiptPrice" type="number" value="${receipt.price}" placeholder="price">
                        <input class="item" name="receiptTypeLimit" type="number" value="${receipt.typeLimit}" placeholder="type limit">
                        <button class="button li-button delete-button" type="button">delete</button>
                    </li>
                </c:forEach>
            </ul>
        </div>

        <div class="form-section">
            <label>Total reimbursement:</label>
            <input name="totalReimbursementLimit" type="number" value="${totalReimbursementLimit}" placeholder="value smaller than 0 will disable this">
            <p>(i) Negative value will disable total reimbursement.</p>
        </div>

        <div class="form-section">
            <label>Distance limit:</label>
            <input name="distanceLimit" type="number" value="${distanceLimit}" placeholder="value smaller than 0 will disable this">
            <p>(i) Negative value will disable distance limit.</p>
        </div>

        <input id="btnSubmit" class="button delete-button" type="submit" value="apply">
    </form>
</main>
</body>

</html>