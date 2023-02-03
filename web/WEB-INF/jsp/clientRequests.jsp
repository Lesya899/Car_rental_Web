<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<html>
<head>
    <title></title>
</head>
<body>
<%@ include file="header.jsp" %><br>
<h1><fmt:message key="page.clientRequests.title"/>:</h1>
<ul>
    <c:forEach var="requestRent" items="${requestsClient}">
        <a href="${pageContext.request.contextPath}/descriptionRequest?rentId=${requestRent.id}">
                    ${requestRent.description}
        </a><br><br>
    </c:forEach>
</ul>
</body>
</html>

