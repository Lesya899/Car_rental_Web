<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>



<html>
<head>
  <title></title>
</head>
<body>
<%@ include file="header.jsp"%><br><br>
<c:forEach var="car" items="${requestScope.cars}">
<table>
  <tr>
    <td>
      <img src="${pageContext.request.contextPath}/images/${car.image}" alt="image not found">
    </td>
    <td>
      <fmt:message key="page.carDescription.brandName"/>: ${car.brandName}<br>
      <fmt:message key="page.carDescription.modelName"/>:  ${car.modelName}<br>
      <fmt:message key="page.carDescription.color"/>: ${car.color}<br>
      <fmt:message key="page.carDescription.status"/>: ${car.status}<br>
      <fmt:message key="page.carDescription.carYear"/>: ${car.carYear}<br>
      <fmt:message key="page.carDescription.price"/>: ${car.rentalPrice}<br>
    </td>
  </tr>
  </table><br><br>
</c:forEach>
</body>
</html>
