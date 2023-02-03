<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>
<head>
    <title></title>
</head>
<body>
<%@ include file="header.jsp" %><br>
<style>
    <%@include file="/WEB-INF/css/styles.css"%>
</style>
<div class="box">
<h1><fmt:message key="page.processRequest.title"/>:</h1>
<ul>
    <fmt:message key="page.makeRequest.dataStart"/>: ${information.dateStart}<br>
    <fmt:message key="page.makeRequest.terminationCarRental"/>: ${information.terminationCarRental}<br>
    <fmt:message key="page.processRequest.requestStatus"/>: ${information.requestStatus}<br>
    <fmt:message key="page.processRequest.message"/>: ${information.message}

</ul>
</div>
</body>
</html>
