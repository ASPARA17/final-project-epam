<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${not empty language}">
        <fmt:setLocale value="${sessionScope.language}"/>
    </c:when>
    <c:when test="${empty language}">
        <fmt:setLocale value="en"/>
    </c:when>
</c:choose>

<fmt:setBundle basename="language"/>
<html>
<head>
</head>
<body>

        <div class="mastfoot mt-auto">
            <div class="inner">
                <p><fmt:message key="footer.text"/></p>
            </div>
        </div>

<%--        <div class="layout-footer">--%>
<%--            <div class="container_log">--%>
<%--                <div class="layout-footer__copyright"><fmt:message key="footer.text"/></div>--%>
<%--            </div>--%>
</body>
</html>
