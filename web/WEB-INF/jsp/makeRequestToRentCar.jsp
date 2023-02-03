<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<html>
<head>
    <title></title>
</head>
<body>
<%@ include file="header.jsp"%><br>
<form action="${pageContext.request.contextPath}/makeRequestToRentCar" method="post">
    <label for="brandId"><fmt:message key="page.carDescription.brandName"/>:
        <select name="brand" id="brandId">
            <c:forEach items="${car}" var="car">
                <option value="${car.brandName}">${car.brandName}</option>
            </c:forEach>
        </select>
    </label><br><br>
    <label for="modelId"><fmt:message key="page.carDescription.modelName"/>:
        <select name="model" id="modelId">
            <c:forEach items="${car}" var="car">
                <option value="${car.modelName}">${car.modelName}</option>
            </c:forEach>
        </select>
    </label><br><br>
    <label for="firstNameId"><fmt:message key="page.makeRequest.firstName"/>:
        <input type="text" name="firstName" id="firstNameId" required>
    </label><br><br>
    <label for="lastNameId"><fmt:message key="page.makeRequest.lastName"/>:
        <input type="text" name="lastName" id="lastNameId" required>
    </label><br><br>
    <label for="phoneNumberId"><fmt:message key="page.makeRequest.phoneNumber"/>:
        <input type="text" name="phoneNumber" id="phoneNumberId" required>
    </label><br><br>
    <label for="passportId"><fmt:message key="page.makeRequest.passport"/>:
        <input type="text" name="passport" id="passportId" required>
    </label><br><br>
    <label for="dataStartId"><fmt:message key="page.makeRequest.dataStart"/>:
        <input type="date" name="dataStart" id="dataStartId" required>
    </label><br><br>
    <label for="terminationCarRentalId"><fmt:message key="page.makeRequest.terminationCarRental"/>:
        <input type="date" name="terminationCarRental" id="terminationCarRentalId" required>
    </label><br><br>
    <label for="drivingExperienceId"><fmt:message key="page.makeRequest.drivingExperience"/>:
        <input type="text" name="drivingExperience" id="drivingExperienceId" required>
    </label><br><br>
    <button type="submit"><fmt:message key="page.login.submit.button"/></button>
    <c:if test="${param.error != null}">
        <div style="color: red">
            <span><fmt:message key="page.makeRequest.error"/></span>
        </div>
    </c:if>
</form>
</body>
</html>