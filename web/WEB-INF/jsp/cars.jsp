<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>



<html>
<head>
  <title></title>
</head>
<body>
<%@ include file="header.jsp"%><br><br>
<ul>
    <c:forEach var="car" items="${requestScope.cars}">
    <img src="${pageContext.request.contextPath}/images/${car.image}" alt="image not found"><br>
      <a href="${pageContext.request.contextPath}/carDescription?carId=${car.id}">${car.brandName }${car.modelName}</a><br><br><br>
  </c:forEach>
    </ul>
</body>
</html>