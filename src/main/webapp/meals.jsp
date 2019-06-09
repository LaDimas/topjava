<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>

<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<table border="1">
    <tr>
        <h1 align="center">
        <th>Дата/время</th>
        <th>Описание</th>
        <th>Калории</th>
        </h1>
    </tr>
    <c:forEach var="num" items="${pageContext.request.getAttribute(\"list\")}">
        <tr style=${num.excess ? "color:red" : "color:green"}>
            <td>
                <javatime:parseLocalDateTime value="${num.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDate" />
                <javatime:format value="${parsedDate}" pattern="dd-MM-yyyy HH:mm" />
            </td>
            <td> ${num.description}</td>
            <td> ${num.calories}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>