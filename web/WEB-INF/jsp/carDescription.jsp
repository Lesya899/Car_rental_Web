<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<html>
<head>
    <title></title>
</head>
<body>
<%@ include file="header.jsp"%><br><br>
<c:forEach var="car" items="${requestScope.car}">
  <img src="${pageContext.request.contextPath}/images/${car.image}" alt="image not found"><br>
  <fmt:message key="page.carDescription.brandName"/>: ${car.brandName}<br><br>
  <fmt:message key="page.carDescription.modelName"/>:  ${car.modelName}<br><br>
  <fmt:message key="page.carDescription.carYear"/>: ${car.carYear}<br><br>
  <fmt:message key="page.carDescription.price"/>: ${car.rentalPrice}<br><br>
<a style="font-size:20px" href="${pageContext.request.contextPath}/makeRequestToRentCar?carId=${car.id}"><fmt:message key="page.carDescription.form"/>:</a><br><br><br>
</c:forEach>
  </body>
</html>

