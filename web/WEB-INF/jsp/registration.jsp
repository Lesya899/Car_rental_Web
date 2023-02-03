<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title></title>
</head>
<body>
<%@ include file="header.jsp" %><br>
<form action="${pageContext.request.contextPath}/registration" method="post">
    <label for="firstNameId"><fmt:message key="page.makeRequest.firstName"/>:
        <input type="text" name="firstName" id="firstNameId">
    </label><br>
    <label for="lastNameId"><fmt:message key="page.makeRequest.lastName"/>:
        <input type="text" name="lastName" id="lastNameId" required>
    </label><br>
    <label for="emailId"><fmt:message key="page.login.email"/>:
        <input type="text" name="email" id="emailId">
    </label><br>
    <label for="passwordId"><fmt:message key="page.login.password"/>:
        <input type="password" name="password" id="passwordId">
    </label><br>
    <label for="role"><fmt:message key="page.login.role"/>:
        <select name="role" id="role">
            <c:forEach var="role" items="${requestScope.roles}">
                <option value="${role}">${role}</option>
            </c:forEach>
        </select>
    </label><br>
    <button type="submit"><fmt:message key="page.login.submit.button"/></button>
    <c:if test="${not empty requestScope.errors}">
        <div style="color: red">
            <c:forEach var="error" items="${requestScope.errors}">
                <span>${error.message}</span>
                <br>
            </c:forEach>
        </div>
    </c:if>
</form>
</body>
</html>
