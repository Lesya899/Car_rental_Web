<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>
</head>
<body>
<%@ include file="header.jsp" %>
<h1><fmt:message key="page.allrequests.title"/>:</h1>
<ul>
    <c:forEach var="requestCar" items="${requestScope.requestCarRent}">
        <li>
            <a href="${pageContext.request.contextPath}/processRequest?requestId=${requestCar.id}">
                    ${requestCar.description}
            </a>
        </li><br><br>
    </c:forEach>
</ul>
</body>
</html>