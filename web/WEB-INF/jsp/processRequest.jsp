<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
  <title></title>
</head>
<body>
<%@ include file="header.jsp" %><br>
<h1><fmt:message key="page.processRequest.title"/>:</h1>
<ul>
  <fmt:message key="page.makeRequest.passport"/>: ${rental.passport}<br>
  <fmt:message key="page.makeRequest.drivingExperience"/>: ${rental.drivingExperience}<br>
  <fmt:message key="page.makeRequest.dataStart"/>: ${rental.dateStart}<br>
  <fmt:message key="page.makeRequest.terminationCarRental"/>: ${rental.terminationCarRental}<br>
  <fmt:message key="page.processRequest.requestStatus"/>: ${rental.requestStatus}<br>
</ul>
<form action="${pageContext.request.contextPath}/changeStatus"  method="get">
  <button type="submit" name="requestId" value="${rental.id}"><fmt:message key="page.processRequest.button"/></button>
</form>
</body>
</html>
