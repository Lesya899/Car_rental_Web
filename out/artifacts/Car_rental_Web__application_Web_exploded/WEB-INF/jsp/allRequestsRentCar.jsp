<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title></title>
    </head>
<body>
<%@ include file="header.jsp"%>
<style>
    <%@include file="/WEB-INF/css/styles.css"%>
</style>
<form action="${pageContext.request.contextPath}/allRequestsRentCar" method="post">
<c:forEach items="${requestCarRent}" var="requestCarRent">
<div class="box">
    <h1>Rental request</h1>
    Request number: ${requestCarRent.id}<br>
    Brand name: ${requestCarRent.brandName}<br>
    Model name: ${requestCarRent.modelName}<br>
    First name: ${requestCarRent.firstName}<br>
    Last name: ${requestCarRent.lastName}<br>
    Phone number: ${requestCarRent.phoneNumber}<br>
    Passport: ${requestCarRent.passport}<br>
    Date start: ${requestCarRent.dateStart}<br>
    Duration: ${requestCarRent.duration}<br>
    <label for="statusId">Status request:
        <select name="status" id="statusId">
            <c:forEach items="${status}" var="status">
                <option value="${status}">${status}</option>
            </c:forEach>
        </select>
    </label><br>
</div>
    <button type="submit" >send</button>
    </c:forEach>
</form>
</body>
</html>