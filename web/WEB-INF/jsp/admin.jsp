<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Admin page</title>
</head>
<body>
<%@ include file="header.jsp"%><br><br>
<a style="font-size:20px" href="${pageContext.request.contextPath}/listCarsForAdmin"><fmt:message key="page.admin.car"/></a><br><br><br>
<a style="font-size:20px" href="${pageContext.request.contextPath}/allRequestsRentCar"><fmt:message key="page.admin.requests"/></a><br><br><br>
</body>
</html>
