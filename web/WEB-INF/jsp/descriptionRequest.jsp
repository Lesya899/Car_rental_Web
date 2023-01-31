<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



<html>
<head>
    <title></title>
</head>
<body>
<h1>Information on the request:</h1>
<ul>

        Date start: ${information.dateStart}<br>
        Duration: ${information.duration}<br>
        Request status: ${information.requestStatus}<br>

</ul>
</body>
</html>
