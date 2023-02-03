<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Page user</title>
</head>
<body>
<%@ include file="header.jsp"%><br><br>
<a style="font-size:20px" href="${pageContext.request.contextPath}/cars"><fmt:message key="page.user.rentACars"/></a><br><br><br>
<a style="font-size:20px" href="${pageContext.request.contextPath}/clientRequests"><fmt:message key="page.user.requestsCarRental"/></a><br><br><br>
</body>
</html>
