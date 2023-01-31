<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
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
  Brand name: ${car.brandName}<br><br>
  Model name:  ${car.modelName}<br><br>
  Car year: ${car.carYear}<br><br>
  Status: ${car.status}<br><br>
  Rental price: ${car.rentalPrice}<br><br>
</c:forEach>
<a style="font-size:20px" href="${pageContext.request.contextPath}/makeRequestToRentCar">Rental car form</a><br><br><br>
</body>
</html>

