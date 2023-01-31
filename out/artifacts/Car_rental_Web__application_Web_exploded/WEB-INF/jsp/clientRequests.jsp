<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<html>
<head>
    <title></title>
</head>
<body>
<h1>Requests:</h1>
<ul>
    <c:forEach var="requestRent" items="${requestsClient}">
        <a href="${pageContext.request.contextPath}/descriptionRequest?rentCarId=${requestRent.id}">
                    ${requestRent.description}
        </a>

    </c:forEach>
</ul>
</body>
</html>

